const express = require("express")
const router = express.Router()

// middleware
const authMiddleware = require("../middlewares/authentication.middleware")

// controller
const indexController = require("../controllers/index.controller")

// route handling
router.get("/", authMiddleware.validToken, indexController.home)
router.get(
  "/admin",
  authMiddleware.validToken,
  authMiddleware.isAdmin,
  indexController.adminonly
)

module.exports = router
