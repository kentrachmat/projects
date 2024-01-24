const createError = require("http-errors")
const cors = require("cors")
const express = require("express")
const path = require("path")
const cookieParser = require("cookie-parser")
const logger = require("morgan")

// routers
const indexRouter = require("./routes/index.route")
const accessRouter = require("./routes/access.route")
const mainRouter = require("./routes/main.route")

// middlewares
const errorMiddleware = require("./middlewares/error.middleware")

// connection to database
const dbConnection = require("./controllers/db.controller.js")

// create app
const app = express()

// view engine setup
app.set("views", path.join(__dirname, "views"))
app.set("view engine", "pug")

// install middlewares and routes
app.use(cors())
app.use(logger("dev"))
app.use(express.json())
app.use(express.urlencoded({ extended: false }))
app.use(cookieParser())
app.use(express.static(path.join(__dirname, "public")))

// cors handling
app.use(function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "http://localhost:3000")
  res.header(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content-Type, Accept"
  )
  next()
})

// routes handling
app.use("/", indexRouter)
app.use("/access", accessRouter)
app.use("/main", mainRouter)

// error handling
app.use(errorMiddleware)
module.exports = app
