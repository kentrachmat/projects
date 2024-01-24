import Mobile from "./Mobile.js"

// default values for a Ball : image and shifts
const BALL_IMAGE_SRC = "./images/balle24.png"
const SHIFT_X = -8
const SHIFT_Y = 0
const SPEED = 8

/**
 * a Ball is a mobile with a ball as image and that bounces in a Game (inside the game's canvas)
 */
export default class Ball extends Mobile {
  /**  build a ball
   *
   * @param  {number} x       the x coordinate
   * @param  {number} y       the y coordinate
   * @param  {Game} theGame   the Game this ball belongs to
   */
  constructor(x, y, theGame) {
    super(x, y, BALL_IMAGE_SRC, SHIFT_X, SHIFT_Y)
    this.theGame = theGame
    this.moving = true
  }

  /**
   * when moving a ball bounces inside the limit of its game's canvas
   */
  move() {
    if (this.y <= 0 || this.y + this.height >= this.theGame.canvas.height) {
      // rebond en haut ou en bas
      this.shiftY = -this.shiftY
    } else if (
      this.theGame.rPaddle.box(this.x + this.width, this.y) ||
      this.theGame.rPaddle.box(this.x + this.width, this.y + this.height)
    ) {
      this.x = this.theGame.rPaddle.x - (this.width + 1)

      // rebound on the right paddle
      this.segmentCollision(this.theGame.rPaddle)
    } else if (
      this.theGame.lPaddle.box(this.x, this.y) ||
      this.theGame.lPaddle.box(this.x, this.y + this.height)
    ) {
      this.x = this.theGame.lPaddle.x + (this.width + 1)
      // rebound on the left paddle
      this.segmentCollision(this.theGame.lPaddle)
    } else if (
      this.x <= 0 ||
      this.x + this.width >= this.theGame.canvas.width
    ) {
      if (this.x <= 0 && this.moving) {
        this.theGame.rScore += 1
      } else if (this.x > 0 && this.moving) {
        this.theGame.lScore += 1
      }
      this.theGame.stop()
      this.moving = false
    }
    super.move()
  }

  /** ball movement dependinng on the segment of the paddle */
  segmentCollision(paddle) {
    const segment = paddle.segmentPaddleCollision(this.y)
    if (this.shiftX < 0) {
      this.shiftX = SPEED - segment
    } else {
      this.shiftX = -(SPEED - segment)
    }

    if (this.shiftY < 0) {
      this.shiftY = -(SPEED - Math.abs(this.shiftX))
    } else {
      this.shiftY = SPEED - Math.abs(this.shiftX)
    }
  }
}
