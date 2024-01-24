import * as msg from "../messageConstants.js"

export default class IOController {
  #io
  #clients

  constructor(io) {
    this.#io = io
    this.#clients = new Array()
  }

  registerSocket(socket) {
    console.log(
      `connection with id ${socket.id} at ${new Date().toLocaleTimeString()}`
    )
    this.setupListeners(socket)
  }

  setupListeners(socket) {
    this.eventKey(socket)
    socket.on("newPlayer", () => this.newPlayer(socket))
    socket.on("disconnect", () => this.leave(socket))
  }

  newPlayer(socket) {
    this.#clients.push(socket.id)
    if (this.#clients.length == 1) {
      socket.emit("p1")
    } else if (this.#clients.length == 2) {
      socket.emit("p2")
      this.#io.emit("startGame")
    } else {
      socket.emit("error")
    }
  }

  eventKey(socket) {
    socket.on("sendScores", (left, right) => {
      socket.broadcast.emit("recvScores", left, right)
    })

    socket.on("ball", (x, y) => {
      socket.broadcast.emit("recvBall", x, y)
    })

    socket.on("upl", (paddle) => {
      socket.broadcast.emit("UpL", paddle)
    })
    socket.on("downl", (paddle) => {
      socket.broadcast.emit("DownL", paddle)
    })
    socket.on("upr", (paddle) => {
      socket.broadcast.emit("UpR", paddle)
    })
    socket.on("downr", (paddle) => {
      socket.broadcast.emit("DownR", paddle)
    })

    socket.on("stopr", (paddle) => {
      socket.broadcast.emit("StopR", paddle)
    })
    socket.on("stopl", (paddle) => {
      socket.broadcast.emit("StopL", paddle)
    })
  }

  leave(socket) {
    console.log(`disconnection from ${socket.id}`)
    const index = this.#clients.indexOf(socket.id)
    if (index > -1) {
      this.#clients.splice(index, 1)
    }
  }
}
