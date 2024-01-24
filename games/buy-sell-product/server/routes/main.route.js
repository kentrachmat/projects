const express = require("express")
const router = express.Router()

// middleware
const authMiddleware = require("../middlewares/authentication.middleware")
const ioMiddleware = require("../middlewares/io.middleware")

// controllers
const mainController = require("../controllers/main.controller")

// file upload configuration
const multer = require("multer")
const { v4: uuidv4 } = require("uuid")
let path = require("path")

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, "public/img")
  },
  filename: function (req, file, cb) {
    cb(null, uuidv4() + "-" + Date.now() + path.extname(file.originalname))
  },
})

const fileFilter = (req, file, cb) => {
  const allowedFileTypes = ["image/jpeg", "image/jpg", "image/png"]
  if (allowedFileTypes.includes(file.mimetype)) {
    cb(null, true)
  } else {
    cb(null, false)
  }
}

let upload = multer({ storage, fileFilter })

// route handling
router.delete(
  "/removeItem",
  authMiddleware.validToken,
  ioMiddleware.validIO,
  mainController.removeItem
)

router.post(
  "/sellItem",
  upload.single("photo"),
  authMiddleware.validToken,
  ioMiddleware.validIO,
  mainController.sellItem
)

router.get("/", mainController.home)
router.get("/itemDetail", authMiddleware.validToken, mainController.itemDetail)
router.get("/userDetail", authMiddleware.validToken, mainController.userDetail)

router.put(
  "/buyItem",
  authMiddleware.validToken,
  ioMiddleware.validIO,
  mainController.buyItem
)
router.put("/updateUser", authMiddleware.validToken, mainController.updateUser)
router.put(
  "/updateItem",
  authMiddleware.validToken,
  ioMiddleware.validIO,
  mainController.updateItem
)

module.exports = router
