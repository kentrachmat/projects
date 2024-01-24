const mongoose = require("mongoose")

// users schema
const userSchema = new mongoose.Schema({
  name: String,
  initial: String,
  login: {
    type: String,
    required: true,
    unique: true,
  },
  email: {
    type: String,
    required: true,
  },
  color: {
    type: String,
    default: "primary",
  },
  password: {
    type: String,
    required: true,
  },
  money: {
    type: Number,
    default: 300,
  },
  admin: {
    type: Boolean,
    default: false,
  },
})

module.exports = userSchema

// model
const dbConnection = require("../controllers/db.controller")
const User = dbConnection.model("User", userSchema, "users")

module.exports.model = User
