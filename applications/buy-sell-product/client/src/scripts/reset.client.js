/*
 * Reset Password handling for the clients
 *
 * version : 2.0
 * author : Benedictus Kent Rachmat & Hichm Karfa
 */

let userlogin
let userpassword
let username

const setup = () => {
  userlogin = document.getElementById("userlogin")
  document.getElementById("reset").addEventListener("click", resetRegister)
}
window.addEventListener("DOMContentLoaded", setup)

const resetRegister = async (_) => {
  const userData = {
    login: userlogin.value,
  }
  const body = JSON.stringify(userData)
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: body,
  }
  const response = await fetch(`/access/reset`, requestOptions)
  if (response.ok) {
    const resetUser = await response.json()
    Swal.fire({
      icon: "success",
      title: "Réinitialisation du compte",
      text: "Mot de passe a été réinitialiser, veuillez vérifier votre e-mail",
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
