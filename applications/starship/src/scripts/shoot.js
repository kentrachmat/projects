
import tir from './../assets/images/tir.png';
import bleu from './../assets/images/bleu.png';
import gris from './../assets/images/gris.png';
import Mobile from './mobile';

export default class Shoot extends Mobile{

  static WIDTH = 10;

  constructor(x, y) {
    let val = Array.from(document.getElementsByName("tir")).find(r => r.checked).value;
    if(val == 1){
      super(x, y, tir, Shoot.WIDTH, 8, 0);
    }
    else if(val == 2){
      super(x, y, bleu, Shoot.WIDTH, 8, 0);
    }
    else if(val == 3){
      super(x, y, gris, Shoot.WIDTH, 8, 0);
    }
  }

  detecter(other){
    let p1_x = Math.max(this._x, other._x);
    let p1_y = Math.max(this._y, other._y);
    let p2_x = Math.min(this._x + Shoot.WIDTH, other._x + other._width);
    let p2_y = Math.min(this._y + Shoot.WIDTH, other._y + other._width);

    return p1_x < p2_x && p1_y < p2_y;
  }

  destroyHit(mobile){
    mobile.forEach(item => {
      if(this.detecter(item) && !item.isDestroyed()){
        item.destroy();
        this.destroyed = true;
      }
    });
  }
}
