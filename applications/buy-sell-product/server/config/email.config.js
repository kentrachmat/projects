// email configuration

var nodemailer = require("nodemailer")

const FROM = "project.info.l3s6@gmail.com"
const transporter = nodemailer.createTransport({
  port: 465,
  host: "smtp.gmail.com",
  auth: {
    user: FROM,
    pass: "tototititata",
  },
  secure: true,
})

module.exports = { transporter: transporter, emailFrom: FROM }
