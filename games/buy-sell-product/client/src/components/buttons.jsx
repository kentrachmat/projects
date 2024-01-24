/**
 * Generate the button
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React from "react"
import { CButton } from "@coreui/react"

import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

const Buttons = (props) => {
  return (
    <CButton
      data-title={props.title}
      data-id={props.id}
      data-price={props.price}
      data-created={props.created}
      color={props.color}
      onClick={props.removeItem}
    >
      {props.text}
    </CButton>
  )
}

export default Buttons
