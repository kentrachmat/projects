const User = require("../models/user.model").model
const Items = require("../models/items.model").model
const bcrypt = require("bcrypt")
const emailSender = require("../lib/email.lib")
const functions = require("../lib/functions.lib")

/**
 * redirect to homepage
 * @param {*} _ unuse request
 * @param {*} res response
 * @returns
 */
module.exports.home = (_, res) => res.redirect("/main.html")

/**
 * get the user detail
 * @param {*} req request
 * @param {*} res response
 */
module.exports.userDetail = async (req, res) => {
  try {
    const user = await User.findById(req.userId)
    res.status(200).json({
      name: user.name,
      id: user._id,
      email: user.email,
      login: user.login,
      money: user.money,
      initial: user.initial,
      color: user.color,
    })
  } catch (error) {
    res.status(400).json(error)
    throw error
  }
}

/**
 * remove item from the list
 * @param {*} req request
 * @param {*} res response
 */
module.exports.removeItem = async (req, res) => {
  try {
    await Items.findByIdAndRemove(req.body.id)
    await req.io.emit(
      "remove",
      `Success`,
      `l'article ${req.body.title} a été supprimé `,
      "green",
      req.userId
    )
    res.status(200).json(null)
  } catch (error) {
    res.status(400).json(error)
    throw error
  }
}

/**
 * get the item detail
 * @param {*} req request
 * @param {*} res response
 */
module.exports.itemDetail = async (req, res) => {
  try {
    const itemsSelf = await Items.find({ created: req.userId, owned: null })
    const items = await Items.find({
      created: { $ne: req.userId },
      owned: null,
    })

    const bought = await Items.find({ owned: req.userId })
    const sold = await Items.find({ created: req.userId, owned: { $ne: null } })

    res.status(200).json({
      items: itemsSelf.concat(items),
      bought: bought,
      sold: sold,
    })
  } catch (error) {
    res.status(400).json(error)
    throw error
  }
}

/**
 * sell them, insert the item to database
 * @param {*} req request
 * @param {*} res reponse
 */
module.exports.sellItem = async (req, res) => {
  try {
    let dataItems
    if (req.file == undefined) {
      dataItems = {
        title: req.body.title,
        price: req.body.price,
        created: req.userId,
      }
    } else {
      dataItems = {
        title: req.body.title,
        price: req.body.price,
        created: req.userId,
        photo: req.file.filename,
      }
    }
    const items = await Items.create(dataItems)
    const user = await User.findById(req.userId)
    req.io.emit(
      "sell",
      `Successs (${user.initial})`,
      `${req.body.title} a été enregistrées -> ${req.body.price} € !`,
      "#007aff",
      req.userId
    )
    res.status(200).json({
      status: "Success (moi)",
      message: `${req.body.title} a été enregistrées -> ${req.body.price} € !`,
      color: "#007aff",
      title: items.title,
      id: items._id,
    })
  } catch (error) {
    res.status(400).json(error)
    throw error
  }
}

/**
 * update user data
 * @param {*} req request
 * @param {*} res reponse
 * @returns
 */
module.exports.updateUser = async (req, res) => {
  let values
  let error = 0
  let msg = ""
  try {
    if (req.body.name == "password" && req.body.value.length < 5) {
      msg = "la longueur de mot de passe doit être supérieure à 5"
      error++
    }
    if (
      req.body.name == "email" &&
      !(await functions.validateEmail(req.body.value))
    ) {
      msg = "format email incorrect"
      error++
    }

    if (req.body.name == "money" && req.body.value <= 0) {
      msg = "l'argent ne peut pas être 0 / négatif"
      error++
    }

    if (error != 0) {
      res.status(400).json({
        status: "Error",
        color: "red",
        message: msg,
      })
      return
    }
    if (req.body.name == "password") {
      const salt = await bcrypt.genSalt()
      const hashPassword = await bcrypt.hash(req.body.value, salt)
      values = hashPassword
      const user = await User.findById(req.userId)
      emailSender.setEmail(user.email)
      emailSender.setTitle("[JSFS L3S6] Réinitialisation du mot de passe")
      emailSender.setBody(emailSender.FR_UPDATE_PASSWORD(req.body.value))
      emailSender.send()
    } else {
      values = req.body.value
    }
    var obj = {}
    obj[req.body.name] = values
    const updatedData = obj
    const user = await User.findByIdAndUpdate(req.userId, updatedData, {
      new: true,
    })
    console.log(updatedData)

    res.status(200).json({
      status: "Success",
      color: "purple",
      message: "les données ont été mises à jour !",
    })
  } catch (error) {
    res.status(400).json(error)
    throw error
  }
}

/**
 * update item data
 * @param {*} req request
 * @param {*} res response
 * @returns
 */
module.exports.updateItem = async (req, res) => {
  let error = 0
  let msg = ""
  try {
    if (req.body.name == "title" && req.body.value.length < 1) {
      msg = "il faut remplir le description !"
      error++
    }

    if (req.body.name == "price" && req.body.value <= 0) {
      msg = "l'argent ne peut pas être 0 / négatif"
      error++
    }

    if (error != 0) {
      res.status(400).json({
        status: "Error",
        color: "red",
        message: msg,
      })
      return
    }
    var obj = {}
    obj[req.body.name] = req.body.value
    const updatedData = obj
    const items = await Items.findByIdAndUpdate(req.body.id, updatedData, {
      new: true,
    })
    await req.io.emit("change")
    res.status(200).json({
      status: "Success",
      color: "purple",
      message: "les données ont été mises à jour !",
    })
  } catch (error) {
    res.status(400).json(error)
    throw error
  }
}

/**
 * update item owner
 * @param {*} req request
 * @param {*} res response
 */
module.exports.buyItem = async (req, res) => {
  try {
    const userBuyer = await User.findById(req.userId)
    const userSeller = await User.findById(req.body.created)
    if (req.body.price >= userBuyer.money) {
      res.status(400).json({
        status: "Error",
        color: "red",
        message: `vous n'avez pas assez d'argent, veuillez ajouter ${
          req.body.price - userBuyer.money + 1
        } plus`,
      })
    } else {
      const updatedDataItem = { owned: req.userId }
      const updatedDataUser = {
        money: parseInt(userBuyer.money) - parseInt(req.body.price),
      }
      const updatedDataUserSeller = {
        money: parseInt(userSeller.money) + parseInt(req.body.price),
      }
      const item = await Items.findByIdAndUpdate(req.body.id, updatedDataItem, {
        new: true,
      })

      const userMoneyBuyer = await User.findByIdAndUpdate(
        req.userId,
        updatedDataUser,
        {
          new: true,
        }
      )

      const userMoneySeller = await User.findByIdAndUpdate(
        req.body.created,
        updatedDataUserSeller,
        {
          new: true,
        }
      )
      await req.io.emit(
        "bought",
        `Success`,
        `l'article ${req.body.title} a été acheté par ${userBuyer.initial}`,
        "green",
        req.body.created,
        updatedDataUserSeller.money,
        updatedDataUser.money
      )
      res.status(200).json({ money: updatedDataUser.money })
    }
  } catch (error) {
    res.status(400).json(error)
    throw error
  }
}
