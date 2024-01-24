import Mobile from "./Mobile.js"

// default values for a Paddle : image and shifts
const PADDLE_IMAGE_SRC = "./images/paddle.png"
const UP = Symbol("up")
const DOWN = Symbol("down")
const NONE = Symbol("none")
const SPEED = 10
const NB_SEGMENTS = 5

/**
 * a Paddle is a mobile that can make the ball rebound depending on the side where the ball hits
 */
export default class Paddle extends Mobile {
  static get UP() {
    return UP
  }
  static get DOWN() {
    return DOWN
  }
  static get NONE() {
    return NONE
  }

  /**  build a Paddle
   *
   * @param  {number} x       the x coordinate
   * @param  {number} y       the y coordinate
   * @param  {Game} theGame   the Game this ball belongs to
   */
  constructor(x, y, theGame) {
    super(x, y, PADDLE_IMAGE_SRC, 0, SPEED)
    this.theGame = theGame
  }

  up() {
    this.steps = -SPEED
    this.moving = Paddle.DOWN
  }

  down() {
    this.steps = SPEED
    this.moving = Paddle.UP
  }

  move(boxs) {
    if (this.moving === Paddle.DOWN) {
      this.y = Math.max(0, this.y + this.steps)
    }
    if (this.moving === Paddle.UP) {
      this.y = Math.min(boxs.height - 80, this.y + this.steps)
    }
  }

  stopMoving() {
    this.moving = Paddle.NONE
  }

  segmentPaddleCollision(ord) {
    const middle = this.y + this.height / 2
    const segment = this.height / 2 / NB_SEGMENTS
    return Math.round(Math.abs((ord - middle) / segment))
  }
}
