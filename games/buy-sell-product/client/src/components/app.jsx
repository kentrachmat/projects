/**
 * Generates the principal page and save the user data such as name, money, etc.
 * Also it contain all the sub variable for the rendering part
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React, { useState, useEffect, useRef } from "react"
import {
  CCol,
  CRow,
  CContainer,
  CToaster,
  CAccordion,
  CAccordionBody,
  CAccordionItem,
  CAccordionHeader,
} from "@coreui/react"
import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

import Header from "./header.jsx"
import Informations from "./informations.jsx"
import FormSeller from "./formSeller.jsx"
import FormBuyer from "./formBuyer.jsx"
import FormSold from "./formSold.jsx"
import TableSelling from "./tableSelling.jsx"
import Notification from "./notification.jsx"
import io from "socket.io-client"

const App = () => {
  const [loading, setLoading] = useState(true)
  const [name, setName] = useState("")
  const [login, setLogin] = useState("")
  const [id, setId] = useState("")
  const [initial, setInitial] = useState("")
  const [email, setEmail] = useState("")
  const [color, setColor] = useState("")
  const [money, setMoney] = useState(0)

  const [dataItems, setDataItems] = useState([])
  const [notif, setNotif] = useState([])

  const [total, setTotal] = useState(0)
  const [bought, setBought] = useState([])
  const [totalSold, setTotalSold] = useState(0)
  const [sold, setSold] = useState([])
  const [time, setTime] = useState("fetching")
  const [counter, setCounter] = useState(1)

  function delay(t, val) {
    return new Promise(function (resolve) {
      setTimeout(function () {
        resolve(val)
      }, t)
    })
  }

  const handleNotification = async (title, message, id, color) => {
    setNotif(
      notif.concat([{ title: title, message: message, id: id, color: color }])
    )
  }

  const fetchItems = async () => {
    setLoading(true)
    const requestOptions = {
      method: "GET",
    }

    const response = await fetch("/main/itemDetail", requestOptions)
    if (response.ok) {
      const data = await response.json()
      setDataItems(data.items)
      setBought(data.bought)
      setSold(data.sold)

      let ttlb = 0
      let ttls = 0
      for (let i = 0; i < data.bought.length; i++) {
        ttlb += data.bought[i].price
      }
      for (let i = 0; i < data.sold.length; i++) {
        ttls += data.sold[i].price
      }
      setTotal(ttlb)
      setTotalSold(ttls)
    } else {
      const error = await response.json()
      if (error.redirectTo) window.location.href = error.redirectTo
      else console.log(`erreur : ${error.message}`)
    }
    setLoading(false)
  }

  const socketHandling = async (id) => {
    const socket = io("http://localhost:3000")
    socket.on("connect_error", () => {
      setTimeout(() => socket.connect(), 5000)
    })
    socket.on("disconnect", () => setTime("server disconnected"))
    socket.on("welcome", () =>
      console.log("connexion avec le serveur succès !")
    )

    socket.emit("welcome")

    socket.on("sell", async (status, msg, color, checkid) => {
      await fetchItems()
      if (checkid != id) {
        await handleNotification(status, msg, "", color)
      }
    })

    socket.on("change", async () => {
      await fetchItems()
    })

    socket.on("bought", async (status, msg, color, idcheck, seller, buyer) => {
      await fetchItems()

      await handleNotification(status, msg, "", color)
      if (id == idcheck) {
        setMoney(seller)
      } else {
        setMoney(buyer)
      }
    })

    socket.on("remove", async (status, msg, color, checkid) => {
      await fetchItems()
      if (checkid != id) {
        await handleNotification(status, msg, "", color)
      }
    })
  }

  const fetchUser = async () => {
    setLoading(true)
    const requestOptions = {
      method: "GET",
    }
    const response = await fetch("/main/userDetail", requestOptions)
    if (response.ok) {
      const user = await response.json()
      setLogin(user.login)
      setName(user.name)
      setMoney(user.money)
      setId(user.id)
      setInitial(user.initial)
      setEmail(user.email)
      setColor(user.color)
      if (counter) {
        socketHandling(user.id)
        setCounter(0)
      }
    } else {
      const error = await response.json()
      if (error.redirectTo) window.location.href = error.redirectTo
      else console.log(`erreur : ${error.message}`)
    }
    setLoading(false)
  }

  useEffect(() => {
    fetchItems()
    fetchUser()
  }, [])

  return (
    <CContainer fluid xxl>
      <Header name={login}></Header>
      <CRow>
        <CCol md={7}>
          <Informations
            initial={initial}
            email={email}
            name={name}
            login={login}
            money={money}
            id={id}
            color={color}
            fetchItems={fetchItems}
            handleNotification={handleNotification}
            setMoney={setMoney}
            setEmail={setEmail}
          ></Informations>
        </CCol>

        <CCol>
          <CToaster>
            {notif.map((item, index) => (
              <Notification
                key={item.id}
                item={item}
                index={index}
                notif={notif}
                setNotif={setNotif}
              ></Notification>
            ))}
          </CToaster>
        </CCol>
      </CRow>
      <CRow>
        <CCol md={4} xs={12}>
          <CAccordion activeItemKey={1}>
            <CAccordionItem itemKey={1}>
              <CAccordionHeader>Sell Item</CAccordionHeader>
              <CAccordionBody>
                <FormSeller
                  handleNotification={handleNotification}
                  fetchItems={fetchItems}
                ></FormSeller>
              </CAccordionBody>
            </CAccordionItem>

            <CAccordionItem itemKey={2}>
              <CAccordionHeader>Buy Item</CAccordionHeader>

              <CAccordionBody>
                <FormBuyer
                  handleNotification={handleNotification}
                  fetchItems={fetchItems}
                  bought={bought}
                  total={total}
                ></FormBuyer>
              </CAccordionBody>
            </CAccordionItem>

            <CAccordionItem itemKey={3}>
              <CAccordionHeader>Sold Item</CAccordionHeader>

              <CAccordionBody>
                <FormSold
                  handleNotification={handleNotification}
                  fetchItems={fetchItems}
                  sold={sold}
                  totalSold={totalSold}
                ></FormSold>
              </CAccordionBody>
            </CAccordionItem>
          </CAccordion>
        </CCol>

        <CCol md={8} xs={12}>
          <TableSelling
            handleNotification={handleNotification}
            dataItems={dataItems}
            id={id}
            fetchItems={fetchItems}
            setMoney={setMoney}
          ></TableSelling>
        </CCol>
      </CRow>
      <br />
    </CContainer>
  )
}

export default App
