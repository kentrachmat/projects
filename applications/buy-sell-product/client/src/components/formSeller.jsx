/**
 * Create a seller card
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React from "react"
import {
  CCard,
  CCardBody,
  CCardTitle,
  CCardSubtitle,
  CCardText,
} from "@coreui/react"

import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"
import FormInput from "./formInput.jsx"

const FormSeller = (props) => {
  return (
    <CCard style={{ width: "20rem" }}>
      <CCardBody>
        <CCardTitle>Sell Item</CCardTitle>
        <CCardSubtitle className="mb-2 text-medium-emphasis">
          Veuillez remplir la description et le prix
        </CCardSubtitle>
        <CCardText>
          <FormInput
            fetchItems={props.fetchItems}
            handleNotification={props.handleNotification}
          ></FormInput>
        </CCardText>
      </CCardBody>
    </CCard>
  )
}

export default FormSeller
