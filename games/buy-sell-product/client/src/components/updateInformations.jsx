/**
 * Generate a update form so the user can modify the values
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React, { useState } from "react"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faPencil, faCheck } from "@fortawesome/free-solid-svg-icons"
import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

const UpdateInformations = (props) => {
  const [type, setType] = useState("text")
  const [loading, setLoading] = useState(true)
  const [value, setValue] = useState(props.name)
  const [property, setProperty] = useState(props.property)

  const changeForm = () => {
    setType("edit")
  }

  const changeValueInput = (event) => {
    setValue(event.target.value)
  }

  const updateForm = async (event) => {
    setLoading(true)
    let res, val
    setType("text")
    val = value == "" || value == 0 ? props.name : value
    const Property = property
    const Id = event.target.dataset.id
    const userData = { name: Property, value: val, id: Id }
    const body = JSON.stringify(userData)
    const requestOptions = {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: body,
    }
    if (props.title == "") {
      res = await fetch("/main/updateItem", requestOptions)
    } else {
      res = await fetch("/main/updateUser", requestOptions)
      if (Property != "password" || Property == undefined) {
        props.update(val)
      }
    }
    const response = res
    if (response.ok) {
      const data = await response.json()
      await props.fetchItems()
      await props.handleNotification(data.status, data.message, "", data.color)
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
    <span>
      {props.title}{" "}
      {type == "text" ? (
        <b>{props.name} </b>
      ) : (
        <input
          type={props.type}
          defaultValue={props.name}
          onChange={changeValueInput}
          required
        />
      )}
      {"  "}
      {props.edit ? (
        type == "text" ? (
          <FontAwesomeIcon icon={faPencil} onClick={changeForm} />
        ) : (
          <FontAwesomeIcon
            data-id={props.title == "" ? props.id : ""}
            icon={faCheck}
            onClick={updateForm}
          />
        )
      ) : (
        ""
      )}
      <br />
    </span>
  )
}

export default UpdateInformations
