import Ball from './ball';

const LEFT = Symbol ("left");
const UP = Symbol ("up");
const RIGHT = Symbol ("right");
const DOWN = Symbol ("down");
const NONE = Symbol ("none");

/* TYPE Obstacle */
export default class Obstacle {

  static get LEFT() { return LEFT;}
  static get UP() { return UP;}
  static get RIGHT() { return RIGHT;}
  static get DOWN() { return DOWN;}
  static get NONE() { return NONE;}

  constructor(x, y, width, height){
    this._x = x;
    this._y = y;
    this._width = width;
    this._height = height;
  }

  draw(context, color = 'black'){
    context.fillStyle = color;
    context.fillRect(this._x, this._y, this._width, this._height);
  }

  moveLeft() {
    this.shiftX = - 10;
    this.moving = Obstacle.LEFT;
  }

  moveRight() {
    this.shiftX = + 10;
    this.moving = Obstacle.RIGHT;
  }

  moveUp() {
    this.shiftY = - 10;
    this.moving = Obstacle.DOWN;
  }

  moveDown() {
    this.shiftY = + 10;
    this.moving = Obstacle.UP;
  }

  move(boxs) {
    if (this.moving === Obstacle.LEFT) {
      this._x = Math.max(0, this._x + this.shiftX);
    }
    if (this.moving === Obstacle.RIGHT) {
      this._x = Math.min(boxs.width - this._width, this._x + this.shiftX);
    }
    if (this.moving === Obstacle.DOWN) {
      this._y = Math.max(0, this._y + this.shiftY);
    }
    if (this.moving === Obstacle.UP) {
      this._y = Math.min(boxs.height - this._height, this._y + this.shiftY);
    }
  }

  stopMoving() {
      this.moving = Obstacle.NONE;
  }
}
