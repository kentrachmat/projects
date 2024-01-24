/**
 * Generate a list for all the product that has been listed by all the users
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React, { useState } from "react"
import {
  CTable,
  CTableHead,
  CTableRow,
  CTableHeaderCell,
  CTableBody,
  CTableDataCell,
  CImage,
} from "@coreui/react"

import UpdateInformations from "./updateInformations.jsx"
import Buttons from "./buttons.jsx"
import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

const TableSelling = (props) => {
  const [loading, setLoading] = useState(true)

  const removeItem = async (event) => {
    const Id = event.target.dataset.id
    const Title = event.target.dataset.title
    event.preventDefault()
    setLoading(true)
    const SellingData = { id: Id, title: Title }
    const body = JSON.stringify(SellingData)
    const requestOptions = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: body,
    }
    const response = await fetch("/main/removeItem", requestOptions)
    if (response.ok) {
      const data = await response.json()
      await props.fetchItems()
      await props.handleNotification(
        "Success",
        `${Title} a été supprimé !`,
        Id,
        "green"
      )
    } else {
      const error = await response.json()
      if (error.redirectTo) window.location.href = error.redirectTo
      else console.log(`erreur : ${error.message}`)
    }
    setLoading(false)
  }

  const submitItem = async (event) => {
    const Id = event.target.dataset.id
    const Title = event.target.dataset.title
    const price = event.target.dataset.price
    const created = event.target.dataset.created
    event.preventDefault()
    setLoading(true)
    const SellingData = { title: Title, id: Id, price: price, created: created }
    const body = JSON.stringify(SellingData)
    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: body,
    }
    const response = await fetch("/main/buyItem", requestOptions)
    if (response.ok) {
      const data = await response.json()
      await props.fetchItems()
      props.setMoney(data.money)
      await props.handleNotification(
        "Success",
        `${Title} a été acheté !`,
        Id,
        "green"
      )
    } else {
      const error = await response.json()
      await props.handleNotification(
        error.status,
        error.message,
        "",
        error.color
      )
      if (error.redirectTo) window.location.href = error.redirectTo
      else console.log(`erreur : ${error.message}`)
    }
    setLoading(false)
  }

  return (
    <CTable hover>
      <CTableHead>
        <CTableRow>
          <CTableHeaderCell scope="col">#</CTableHeaderCell>
          <CTableHeaderCell scope="col">Description</CTableHeaderCell>
          <CTableHeaderCell scope="col">Prix (€)</CTableHeaderCell>
          <CTableHeaderCell scope="col">Date</CTableHeaderCell>
          <CTableHeaderCell scope="col">Action</CTableHeaderCell>
        </CTableRow>
      </CTableHead>
      <CTableBody>
        {props.dataItems.map((item, index) => (
          <CTableRow
            color={item.created == props.id ? "primary" : ""}
            key={item._id}
          >
            <CTableHeaderCell scope="row">{index + 1}</CTableHeaderCell>
            <CTableDataCell>
              {item.created == props.id ? (
                <UpdateInformations
                  id={item._id}
                  name={item.title}
                  title=""
                  edit={true}
                  type="text"
                  property="title"
                  fetchItems={props.fetchItems}
                  handleNotification={props.handleNotification}
                ></UpdateInformations>
              ) : (
                item.title
              )}
            </CTableDataCell>
            <CTableDataCell>
              {item.created == props.id ? (
                <UpdateInformations
                  id={item._id}
                  name={item.price}
                  title=""
                  edit={true}
                  type="number"
                  property="price"
                  fetchItems={props.fetchItems}
                  handleNotification={props.handleNotification}
                ></UpdateInformations>
              ) : (
                item.price
              )}
            </CTableDataCell>
            <CTableDataCell>
              {new Date(item.time).getDate() +
                "/" +
                new Date(item.time).getMonth() +
                "/" +
                new Date(item.time).getFullYear()}
            </CTableDataCell>
            <CTableDataCell>
              {item.created == props.id ? (
                <Buttons
                  color="danger"
                  text="Remove"
                  removeItem={removeItem}
                  id={item._id}
                  title={item.title}
                  price={item.price}
                  created={item.created}
                ></Buttons>
              ) : (
                <Buttons
                  color="primary"
                  text="Buy"
                  removeItem={submitItem}
                  id={item._id}
                  title={item.title}
                  price={item.price}
                  created={item.created}
                ></Buttons>
              )}
              {"  "}
              {item.photo != "" ? (
                <a href={`/img/${item.photo}`}>
                  <CImage
                    rounded
                    thumbnail
                    src={`/img/${item.photo}`}
                    width={60}
                    height={60}
                  />
                </a>
              ) : (
                ``
              )}
            </CTableDataCell>
          </CTableRow>
        ))}
      </CTableBody>
    </CTable>
  )
}

export default TableSelling
