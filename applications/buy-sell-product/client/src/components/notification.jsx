/**
 * Generate a notification for the user
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import React from "react"
import { CToast, CToastHeader, CToastBody, CToastClose } from "@coreui/react"
import "@coreui/coreui/dist/css/coreui.min.css"
import "bootstrap/dist/css/bootstrap.min.css"

const Notification = (props) => {
  return (
    <CToast title="Notification" autohide={false} visible={true}>
      <CToastHeader close>
        <svg
          className="rounded me-2"
          width="20"
          height="20"
          xmlns="http://www.w3.org/2000/svg"
          preserveAspectRatio="xMidYMid slice"
          focusable="false"
          role="img"
        >
          <rect width="100%" height="100%" fill={props.item.color}></rect>
        </svg>
        <strong className="me-auto">{props.item.title}</strong>
        <CToastClose
          className="me-2 m-auto"
          onClick={() => {
            if (props.notif.length == 1) {
              props.setNotif([])
            } else {
              props.notif.splice(props.index, 1)
              props.setNotif([...props.notif])
            }
          }}
        />
      </CToastHeader>
      <CToastBody>{props.item.message}</CToastBody>
    </CToast>
  )
}

export default Notification
