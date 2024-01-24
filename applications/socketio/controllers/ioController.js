import * as msg from "../public/scripts/messageConstants.js"

export default class IOController {
  #io
  #clients
  #interval

  constructor(io) {
    this.#io = io
    this.#clients = new Array()
  }

  randomValue(socket) {
    // chaque utilisateur a des données différentes
    socket.emit(msg.RECV_DATA, this.generateRandomValue(2, 8))

    // chaque utilisateur a les mêmes données
    //this.#io.emit(msg.RECV_DATA, this.generateRandomValue(2, 8))
  }

  generateRandomValue(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min)
  }

  registerSocket(socket) {
    console.log(
      `connection with id ${socket.id} at ${new Date().toLocaleTimeString()}`
    )
    this.setupListeners(socket)
  }

  setupListeners(socket) {
    socket.on(msg.GREATINGS, () => this.greatings(socket))
    socket.on("disconnect", () => this.leave(socket))
    //clearInterval(this.#interval)
    this.#interval = setInterval(() => {
      this.randomValue(socket)
    }, 2000)
  }

  greatings(socket, userName) {
    console.log(`greatings received from id : ${socket.id}`)
    this.#clients.push(socket.id)
    socket.emit(msg.WELCOME)
  }

  leave(socket) {
    console.log(`disconnection from ${socket.id}`)
    const index = this.#clients.indexOf(socket.id)
    if (index > -1) {
      this.#clients.splice(index, 1)
    }
  }
}
