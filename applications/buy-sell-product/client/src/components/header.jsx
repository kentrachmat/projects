/**
 * Application header
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React, { useState } from "react"
import {
  CContainer,
  CCollapse,
  CHeader,
  CNavItem,
  CHeaderBrand,
  CHeaderNav,
  CNavLink,
} from "@coreui/react"
import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

import Swal from "sweetalert2"
import withReactContent from "sweetalert2-react-content"

const Header = (props) => {
  const MySwal = withReactContent(Swal)
  const [visible, setVisible] = useState(true)

  const logout = async () => {
    const requestOptions = {
      method: "GET",
    }
    const response = await fetch(`/access/logout`, requestOptions)
    if (response.ok) {
      await MySwal.fire({
        title: "Success",
        html: <p>Déconnexion réussi</p>,
        icon: "success",
      })
      window.location.href = "/"
    }
  }

  return (
    <>
      <CHeader>
        <CContainer fluid>
          <CHeaderBrand href="#">Bienvenue, {props.name}</CHeaderBrand>
          <CCollapse className="header-collapse" visible={visible}>
            <CHeaderNav>
              <CNavItem>
                <CNavLink href="#" active>
                  Home
                </CNavLink>
              </CNavItem>
              <CNavItem>
                <CNavLink href="#" onClick={logout}>
                  Logout
                </CNavLink>
              </CNavItem>
            </CHeaderNav>
          </CCollapse>
        </CContainer>
      </CHeader>
    </>
  )
}

export default Header
