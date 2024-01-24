import Ball from "./Ball.js"
import Paddle from "./Paddle.js"

/**
 * a Game animates a ball bouncing in a canvas
 */
export default class Game {
  /**
   * build a Game
   *
   * @param  {Canvas} canvas the canvas of the game
   */

  constructor(canvas) {
    this.raf = null
    this.socket = io()
    this.canvas = canvas
    this.lScore = 0
    this.rScore = 0
    this.initBall()
    this.lPaddle = new Paddle(
      this.canvas.width / 16,
      this.canvas.height / 2,
      this
    )
    this.rPaddle = new Paddle(
      this.canvas.width - this.canvas.width / 16 - 30,
      this.canvas.height / 2,
      this
    )
    this.keyDownActionHandler()
    this.keyUpActionHandler()
    this.receivedData()
    this.socketControl()
  }

  socketControl() {
    this.socket.on("p1", function () {
      console.log("connection player ONE with server establised")
      document.getElementById("player").innerHTML = `Player 1`
      document.getElementById("score").innerHTML = `Waiting for player 2 ...`
    })
    this.socket.on("p2", function () {
      console.log("connection player TWO with server establised")
      document.getElementById("player").innerHTML = `Player 2`
    })

    this.socket.on("error", function () {
      console.log("connection player with server error, LIMIT player : 2")
      console.log("disconnected from the server automatically")
      document.getElementById("player").innerHTML = `Connection Error`
    })
  }

  receivedData() {
    this.socket.on("recvScores", (left, right) => {
      this.lScore = left
      this.rScore = right
      this.displayScores()
    })

    this.socket.on("recvBall", (x, y) => {
      this.initBall()
      this.animate()
    })

    this.socket.on("UpL", (paddle) => {
      this.lPaddle.y = paddle
      this.lPaddle.up()
    })

    this.socket.on("DownL", (paddle) => {
      this.lPaddle.y = paddle
      this.lPaddle.down()
    })

    this.socket.on("UpR", (paddle) => {
      this.rPaddle.y = paddle
      this.rPaddle.up()
    })

    this.socket.on("DownR", (paddle) => {
      this.rPaddle.y = paddle
      this.rPaddle.down()
    })

    this.socket.on("StopL", (paddle) => {
      this.lPaddle.y = paddle
      this.lPaddle.stopMoving()
    })

    this.socket.on("StopR", (paddle) => {
      this.rPaddle.y = paddle
      this.rPaddle.stopMoving()
    })
  }

  initBall() {
    this.ball = new Ball(this.canvas.width / 2, this.canvas.height / 2, this)
  }

  init() {
    this.socket.emit("newPlayer")
  }
  /** start the animation and make the ball move */
  start() {
    this.animate()
  }

  /** stop the ball move */
  stop() {
    window.cancelAnimationFrame(this.raf)
    this.ball.stopMoving()
    this.socket.emit("sendScores", this.lScore, this.rScore)
  }

  restart() {
    this.socket.emit("ball", this.ball.x, this.ball.y)
  }

  /** draw the paddle and ball */
  animate() {
    this.moveAndDraw()
    this.raf = window.requestAnimationFrame(this.animate.bind(this))
  }

  /** move and draw components */
  moveAndDraw() {
    const ctxt = this.canvas.getContext("2d")
    ctxt.clearRect(0, 0, this.canvas.width, this.canvas.height)
    this.ball.move()

    this.ball.draw(ctxt)

    this.lPaddle.draw(ctxt)
    this.lPaddle.move(this.canvas)

    this.rPaddle.draw(ctxt)
    this.rPaddle.move(this.canvas)
  }

  /** displays the score of the players */
  displayScores() {
    document.getElementById(
      "score"
    ).innerHTML = `Score => P1 : ${this.lScore} - P2 : ${this.rScore}`
  }

  /** keyup event listener */
  keyUpActionHandler() {
    window.addEventListener("keyup", (event) => {
      event.preventDefault()
      switch (event.key) {
        case "ArrowUp":
        case "ArrowDown":
          this.rPaddle.stopMoving()
          this.socket.emit("stopr", this.rPaddle.y)
          break
        case "w":
        case "s":
          this.lPaddle.stopMoving()
          this.socket.emit("stopl", this.lPaddle.y)
          break
        default:
          return
      }
    })
  }

  /** keydown event listener */
  keyDownActionHandler() {
    window.addEventListener("keydown", (event) => {
      event.preventDefault()
      switch (event.key) {
        case "ArrowUp":
          this.rPaddle.up()
          this.socket.emit("upr", this.rPaddle.y)
          break
        case "ArrowDown":
          this.rPaddle.down()
          this.socket.emit("downr", this.rPaddle.y)
          break
        case "w":
        case "z":
          this.lPaddle.up()
          this.socket.emit("upl", this.lPaddle.y)
          break
        case "s":
          this.lPaddle.down()
          this.socket.emit("downl", this.lPaddle.y)
          break
        case " ":
          if (!this.ball.moving) {
            this.initBall()
          }
          break
        default:
          return
      }
    })
  }
}
