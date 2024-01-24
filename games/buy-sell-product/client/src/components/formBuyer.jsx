/**
 * Buyer history menu, updated when a product is bought
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
  CListGroupItem,
  CListGroup,
  CButton,
  CBadge,
} from "@coreui/react"

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faImage } from "@fortawesome/free-solid-svg-icons"
import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

const FormBuyer = (props) => {
  return (
    <CCard style={{ width: "20rem" }}>
      <CCardBody>
        <CCardTitle>Buy Item</CCardTitle>
        <CCardSubtitle className="mb-2 text-medium-emphasis">
          Dernier Objet Acheté
          <br /> Total : {props.total} €
        </CCardSubtitle>
        <CCardText>
          <CListGroup>
            {props.bought.map((item, index) => (
              <CListGroupItem key={index}>
                <CButton color="primary">
                  {item.title}
                  {"   "}
                  <CBadge color="success">{item.price + " €"}</CBadge>
                </CButton>
                {"  "}
                {item.photo != "" ? (
                  <a href={`/img/${item.photo}`}>
                    <FontAwesomeIcon icon={faImage} />
                  </a>
                ) : (
                  ``
                )}
              </CListGroupItem>
            ))}
          </CListGroup>
        </CCardText>
      </CCardBody>
    </CCard>
  )
}

export default FormBuyer
