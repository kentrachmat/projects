
import flysaucer from './../assets/images/flyingSaucer-petit.png';

import Mobile from './mobile';

export default class Saucer extends Mobile {

  static WIDTH = 36;

  constructor(x, y) {
    super(x,y, flysaucer, Saucer.WIDTH, -3, 0);
  }

  destroy(){
    this.destroyed = true;
  }

  fall(){
    this._x +=0;
    this._y +=3;
  }

  move(context){
    if(this.destroyed){
      this.fall();
    }
    else {
      this._x += this.deltaX;
    }
    /*
    if (this._x + this.deltaX > -10) {
      this._x += this.deltaX;
    }
    if (this._x + this.deltaX < -10 && this._y < context.canvas.height) {
      this._y += this.deltaY;
    }
    */
  }
}
