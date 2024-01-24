
import ballon from './../assets/images/vaisseau-ballon-petit.png';
import Mobile from './mobile';

const IMMOBILE = Symbol('immobile');
const UP = Symbol('up');
const DOWN = Symbol('down');

class MoveState {
  static get IMMOBILE() { return IMMOBILE; }
  static get UP() { return UP; }
  static get DOWN() { return DOWN; }
}

export default class Starship extends Mobile{

  static WIDTH = 30;

  constructor(y) {
    super(40, y, ballon, Starship.WIDTH, 0, 8);
    this.moving = MoveState.IMMOBILE;
  }

  get up(){
    return this.moving === MoveState.UP;
  }

  get down(){
    return this.moving === MoveState.DOWN;
  }

  moveUp() {
    this.deltaY = - Math.abs(this.deltaY);
    this.moving = MoveState.UP;
  }

  moveDown() {
    this.deltaY = + Math.abs(this.deltaY);
    this.moving = MoveState.DOWN;
  }

  move(boxs) {
    if (this.moving === MoveState.UP) {
      this._y = Math.max(0, this._y + this.deltaY);
    }
    if (this.moving === MoveState.DOWN) {
      this._y = Math.min(boxs.height - this.image.height, this._y + this.deltaY);
    }
  }

  stopMoving() {
      this.moving = MoveState.IMMOBILE;
  }
}
