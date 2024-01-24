/**
 * Information of the user
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React from "react"
import { CCallout, CAvatar } from "@coreui/react"
import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

import UpdateInformations from "./updateInformations.jsx"

const Informations = (props) => {
  return (
    <CCallout color={props.color}>
      <CAvatar color={props.color} size="xl">
        {props.initial}
      </CAvatar>
      <br />
      <UpdateInformations
        name={props.id}
        title="ID : "
        edit={false}
        type="text"
        property="id"
      ></UpdateInformations>
      <UpdateInformations
        name={props.login}
        title="Login : "
        edit={false}
        type="text"
        property="login"
      ></UpdateInformations>
      <UpdateInformations
        name={props.name}
        title="Nom : "
        edit={false}
        type="text"
        property="name"
      ></UpdateInformations>
      <UpdateInformations
        name={props.money}
        title="D'argent (€) : "
        edit={true}
        type="number"
        property="money"
        update={props.setMoney}
        fetchItems={props.fetchItems}
        handleNotification={props.handleNotification}
      ></UpdateInformations>
      <UpdateInformations
        name={props.email}
        title="Email : "
        edit={true}
        type="email"
        property="email"
        update={props.setEmail}
        fetchItems={props.fetchItems}
        handleNotification={props.handleNotification}
      ></UpdateInformations>
      <UpdateInformations
        name=""
        title="Mot de passe : "
        edit={true}
        type="password"
        property="password"
        fetchItems={props.fetchItems}
        handleNotification={props.handleNotification}
      ></UpdateInformations>
    </CCallout>
  )
}

export default Informations
