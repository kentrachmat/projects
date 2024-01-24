/*
 * Create React root element and insert it into document
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

import ReactDOM from "react-dom"
import App from "../components/app.jsx"

const bootstrapReact = () =>
  ReactDOM.render(<App />, document.getElementById("react"))

window.addEventListener("DOMContentLoaded", bootstrapReact)
