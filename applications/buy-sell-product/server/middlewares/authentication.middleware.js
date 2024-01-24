const User = require("../models/user.model").model

const jwt = require("jsonwebtoken")
const jwtConfig = require("../config/jwt.config")

/**
 * check if the token valid or not
 * @param {*} req request
 * @param {*} res reponse
 * @param {*} next next function
 */
const validToken = (req, res, next) => {
  try {
    const token = req.cookies.token
    const decoded = jwt.verify(token, jwtConfig.SECRET_TOKEN)
    console.log(`decoded : ${decoded.id}`)
    // add user id to request : retrieved from token since added to payload
    req.userId = decoded.id
    next()
  } catch (err) {
    console.log(`erreur JWT : ${err.message}`)
    if (req.headers["sec-fetch-dest"] === "empty") {
      // req comes from a fetch() ?
      console.log("sec-fetch-dest: EMPTY")
      res.status(401).json({ msg: "Erreur JWT", redirectTo: "/access/login" })
    } else {
      console.log(
        `sec-fetch-dest: ${req.headers["sec-fetch-dest"].toUpperCase()}`
      )
      res.status(301).redirect("/access/login")
    }
  }
}

/**
 * it would be more relevant to put the "admin" status
 * in the payload of the JWT token this is more in line with the "stateless" approach of JWT
 * @param {*} req request
 * @param {*} res reponse
 * @param {*} next next function
 */
const adminAuthentication = async (req, res, next) => {
  const userId = req.userId
  const user = await User.findById(userId)
  if (user.admin) {
    next()
  } else {
    res.status(401).json({ message: "Admin : accès refusé" })
  }
}

/** module exports */
module.exports.validToken = validToken
module.exports.isAdmin = adminAuthentication
