const express = require("express")
const router = express.Router()

// controller
const accessController = require("../controllers/access.controller")

// route handling
router.get("/login", accessController.loginForm)
router.post("/login", accessController.login)

router.get("/register", accessController.registerForm)
router.post("/register", accessController.register)

router.get("/reset", accessController.resetForm)
router.post("/reset", accessController.reset)

router.get("/logout", accessController.logout)

module.exports = router
