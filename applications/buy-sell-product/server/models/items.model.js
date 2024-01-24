const mongoose = require("mongoose")

// items schema
const itemsSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  created: mongoose.ObjectId,
  time: {
    type: String,
    default: new Date(),
  },
  owned: {
    type: mongoose.ObjectId,
    default: null,
  },
  photo: {
    type: String,
    default: "",
  },
})

module.exports = itemsSchema

// model
const dbConnection = require("../controllers/db.controller")
const Items = dbConnection.model("Items", itemsSchema, "items")

module.exports.model = Items
