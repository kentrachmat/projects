const bcrypt = require("bcrypt")
const User = require("../models/user.model").model
const jwt = require("jsonwebtoken")
const jwtConfig = require("../config/jwt.config")
const emailSender = require("../lib/email.lib")
const functions = require("../lib/functions.lib")

const registerForm = (_, res) => res.redirect("/register.html")
const loginForm = (_, res) => res.redirect("/login.html")
const resetForm = (_, res) => res.redirect("/reset.html")

/**
 * generate the first letter of each word
 * @param {string} name the user name
 * @returns the first letter of each word
 */
const getFirstLetters = (name) => {
  var splitStr = name.toLowerCase().split(" ")
  for (var i = 0; i < splitStr.length; i++) {
    splitStr[i] = splitStr[i].charAt(0).toUpperCase()
  }
  return splitStr.join("")
}

/**
 * generate a random pasasword
 * @param {int} length password length
 * @returns a random pasasword
 */
const makePass = (length) => {
  var result = ""
  var characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
  var charactersLength = characters.length
  for (var i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength))
  }
  return result
}

/**
 * creates the entry corresponding to the user from the provided information,
 * the password is encrypted before being stored in the database
 * @param {*} req request
 * @param {*} res response
 */
const register = async (req, res) => {
  // hash password using bcrypt
  const salt = await bcrypt.genSalt()
  const hashPassword = await bcrypt.hash(req.body.password, salt)

  console.log(`register , body.admin : ${req.body.admin}`)

  try {
    let error = 0
    let msg = ""
    if (req.body.login.length < 5) {
      msg = "la longueur de login doit être supérieure à 5"
      error++
    }
    if (req.body.password.length < 5) {
      msg = "la longueur de mot de passe doit être supérieure à 5"
      error++
    }
    if (!(await functions.validateEmail(req.body.email))) {
      msg = "format email incorrect"
      error++
    }
    if (error != 0) {
      res.status(409).json({ message: msg })
    } else {
      let ccl = ["primary", "success", "danger", "warning", "info", "dark"]
      const userData = {
        ...req.body,
        initial: getFirstLetters(req.body.name),
        password: hashPassword,
        color: ccl[Math.floor(Math.random() * ccl.length)],
      }

      const newUser = await User.create(userData) // save user in the database
      delete userData.password // password is not sent back in request

      console.log("utilisateur créé")

      emailSender.setEmail(req.body.email)
      emailSender.setTitle(
        "[JSFS L3S6] Enregistrement du compte - " + req.body.name
      )
      emailSender.setBody(
        emailSender.FR_REGISTRATION(
          req.body.login,
          req.body.password,
          req.body.name
        )
      )
      emailSender.send()

      res.status(201).json(userData)
    }
  } catch (err) {
    console.log(`pb création utilisateur ${err.message}`)
    console.log(err)
    if (err.name === "MongoServerError" && err.code === 11000) {
      res.status(409).send({ message: "L'utilisateur existe déjà!" })
    } else {
      res.status(409).json({ message: err.message })
    }
  }
}

/**
 * reset the user password
 * @param {*} req request
 * @param {*} res response
 */
const reset = async (req, res) => {
  try {
    const user = await User.findOne({ login: req.body.login })
    if (user) {
      const salt = await bcrypt.genSalt()
      let pass = makePass(6)
      const hashPassword = await bcrypt.hash(pass, salt)
      const reset = await User.findByIdAndUpdate(
        user.id,
        { password: hashPassword },
        {
          new: true,
        }
      )

      emailSender.setEmail(user.email)
      emailSender.setTitle(
        "[JSFS L3S6] Réinitialisation du compte - " + user.name
      )
      emailSender.setBody(emailSender.FR_RESET(user.login, pass, user.name))
      emailSender.send()

      res.status(201).json(reset)
    } else {
      console.log(`Utilisateur ${req.body.login} non trouvé`)
      res
        .status(401)
        .json({ message: `Utilisateur ${req.body.login} non trouvé` })
    }
  } catch (err) {
    console.log(`pb connexion ${err.message}`)
    res.status(500).redirect("/access/reset")
  }
}

/**
 * finds if there is a user corresponding to the username/password provided
 * if so a JWT token is created and returned
 * @param {*} req request
 * @param {*} res response
 */
const login = async (req, res) => {
  try {
    // check if user exist
    const user = await User.findOne({ login: req.body.login })
    if (user) {
      // check password
      const validPassword = await bcrypt.compare(
        req.body.password,
        user.password
      )
      if (!validPassword)
        // wrong password
        return res.status(401).json({ message: "Mot de passe incorrect." })

      // create and send token
      const token = jwt.sign({ id: user._id }, jwtConfig.SECRET_TOKEN, {
        expiresIn: "60 days",
      })
      console.log(`login : ${token}`)
      res.cookie("token", token, {
        maxAge: 900000,
        httpOnly: true,
        sameSite: "strict",
      }) // secure : true (avec https)
      res.status(200).json({ message: "Utilisateur connecté" })
    } else {
      // unknown login
      console.log(`Utilisateur ${req.body.login} inconnu`)
      res.status(401).json({ message: `Utilisateur ${req.body.login} inconnu` })
    }
  } catch (err) {
    console.log(`pb connexion ${err.message}`)
    res.status(500).redirect("/access/register")
  }
}

/**
 * logout user from the application, set token value to empty string
 * @param {*} req request
 * @param {*} res response
 */
const logout = (req, res) => {
  res.cookie("token", "", { maxAge: 2000, httpOnly: true, sameSite: "strict" }) // secure : true
  res.status(200).json({ message: "Utilisateur déconnecté" })
}

/** module exports */
module.exports.login = login
module.exports.loginForm = loginForm
module.exports.register = register
module.exports.registerForm = registerForm
module.exports.logout = logout
module.exports.resetForm = resetForm
module.exports.reset = reset
