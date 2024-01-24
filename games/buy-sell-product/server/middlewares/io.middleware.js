// socket.io middleware

let sock
let ios
const init = (io) => {
  ios = io
  io.on("connection", (socket) => {
    setupListeners(socket)
  })
}

const setupListeners = (socket) => {
  sock = socket
  socket.join("room1")
  socket.on("welcome", () => greatings(socket))
  socket.on("disconnect", () => leave(socket))
}

const greatings = (socket) => {
  console.log(`greatings received from id : ${socket.id}`)
  socket.emit("welcome")
}

const leave = (socket) => {
  console.log(`disconnection from ${socket.id}`)
}

const validIO = (req, res, next) => {
  req.socket = sock
  req.io = ios
  next()
}

module.exports.init = init
module.exports.validIO = validIO
