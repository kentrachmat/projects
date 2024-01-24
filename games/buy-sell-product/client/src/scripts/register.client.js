/*
 * Registration handling for the clients
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

let userlogin
let userpassword
let username

const setup = () => {
  username = document.getElementById("username")
  userlogin = document.getElementById("userlogin")
  userpassword = document.getElementById("userpassword")
  useremail = document.getElementById("useremail")
  document
    .getElementById("register")
    .addEventListener("click", () => register(false))
  document
    .getElementById("adminregister")
    .addEventListener("click", adminRegister)
}
window.addEventListener("DOMContentLoaded", setup)

const register = async (admin) => {
  const userData = {
    name: username.value,
    login: userlogin.value,
    password: userpassword.value,
    email: useremail.value,
    admin: admin || false,
  }
  console.log(`admin : ${userData.admin}`)
  const body = JSON.stringify(userData)
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: body,
  }
  const response = await fetch(`/access/register`, requestOptions)
  if (response.ok) {
    const createdUser = await response.json()
    console.log(`utilisateur enregistré : ${JSON.stringify(createdUser)}`)

    Swal.fire({
      title: `Le compte de ${username.value} a été créé`,
      text: "Voulez-vous en créer un autre ?",
      icon: "success",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Oui",
      cancelButtonText: "Non",
    }).then((result) => {
      if (!result.isConfirmed) {
        window.location.href = "/access/login"
      } else {
        userlogin.value = ""
        username.value = ""
        userpassword.value = ""
        useremail.value = ""
      }
    })
  } else {
    const error = await response.json()
    console.log(`erreur : ${error.message}`)
    Swal.fire({
      icon: "error",
      title: "Erreur !",
      text: error.message,
    })
  }
}

const adminRegister = () => register(true)
