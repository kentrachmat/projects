/**
 * Create a form to be filled by the seller of the product
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React, { useState } from "react"
import {
  CForm,
  CCol,
  CFormLabel,
  CFormInput,
  CFormFeedback,
  CButton,
} from "@coreui/react"

import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"
import axios from "axios"

const FormInput = (props) => {
  const [validated, setValidated] = useState(false)
  const [description, setDescription] = useState("")
  const [prix, setPrix] = useState(0)
  const [loading, setLoading] = useState(true)
  const [image, setImage] = useState("")

  const onFileChange = (e) => {
    setImage(e.target.files[0])
  }

  const handleSubmit = (event) => {
    const form = event.currentTarget
    let desc = event.target[0].value
    let prix = event.target[1].value
    if (form.checkValidity() === false) {
      event.preventDefault()
      event.stopPropagation()
    }
    event.preventDefault()
    setValidated(true)
    sendData(desc, prix)
  }

  const sendData = async (desc, prix) => {
    setLoading(true)
    const formData = new FormData()
    formData.append("photo", image)
    formData.append("title", desc)
    formData.append("price", prix)

    axios
      .post("main/sellItem", formData)
      .then(async (res) => {
        if (res.status == 200) {
          const data = res.data
          await props.fetchItems()
          await props.handleNotification(
            data.status,
            data.message,
            data.id,
            data.color
          )
        } else {
          const error = res.data
          if (error.redirectTo) window.location.href = error.redirectTo
          else console.log(`erreur : ${error.message}`)
        }
      })
      .catch((err) => {
        console.log(err)
      })
    setLoading(false)
  }

  return (
    <CForm
      className="row g-3 needs-validation"
      validated={validated}
      onSubmit={handleSubmit}
      encType="multipart/form-data"
    >
      <CCol xs={12}>
        <CFormLabel htmlFor="validationDefault02">Description</CFormLabel>

        <CFormInput
          type="text"
          id="validationDefault02"
          defaultValue={description}
          placeholder="description"
          required
        />

        <CFormFeedback valid>Description validé !</CFormFeedback>
        <CFormFeedback invalid>Description invalide !</CFormFeedback>
      </CCol>

      <CCol xs={12}>
        <CFormLabel htmlFor="validationDefault03">Prix (€)</CFormLabel>

        <CFormInput
          type="number"
          defaultValue={prix}
          id="validationDefault03"
          min={1}
          required
        />
        <CFormFeedback valid>Prix validé !</CFormFeedback>
        <CFormFeedback invalid>Prix invalide !</CFormFeedback>
      </CCol>
      <CCol xs={12}>
        <CFormLabel htmlFor="formFile">Images</CFormLabel>
        <CFormInput
          type="file"
          id="formFile"
          accept=".png, .jpg, .jpeg"
          name="photo"
          onChange={onFileChange}
        />
      </CCol>

      <CCol xs={12}>
        <CButton color="primary" type="submit">
          Submit
        </CButton>
      </CCol>
    </CForm>
  )
}

export default FormInput
