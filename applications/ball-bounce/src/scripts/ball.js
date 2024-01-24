
// la source de l'image à utiliser pour la balle
import ballImgSrc from './assets/images/ball.png';

/* TYPE Ball */
export default class Ball {

    static BALL_WIDTH = 48;

    constructor(x,y, dX=3, dY=-2){
      this._x = x;
      this._y = y;
      this.deltaX = dX;
      this.deltaY = dY;
      this.ball = this.createImage();
    }

    draw(context) {
      context.drawImage(this.ball, this._x, this._y);
    }

    move(context){
      this._x += this.deltaX;
      this._y += this.deltaY;
      if (this._y + this.deltaY > context.canvas.height - 40 || this._y + this.deltaY < 0) {
        this.deltaY = -this.deltaY;
      }
      if (this._x + this.deltaX > context.canvas.width - 40|| this._x + this.deltaX < 0) {
        this.deltaX = -this.deltaX;
      }
    }

    collisionWith(obstacle){
      const p1x = Math.max(this._x, obstacle._x);
      const p2x = Math.min(obstacle._x + obstacle._width, this._x + Ball.BALL_WIDTH);
      const p1y = Math.max(this._y, obstacle._y);
      const p2y = Math.min(obstacle._y + obstacle._height, this._y + Ball.BALL_WIDTH);
      return p1x <= p2x && p1y <= p2y;
    }

    /* crée l'objet Image à utiliser pour dessiner cette balle */
    createImage() {
  	   const ballImg = new Image();
	     ballImg.width = Ball.BALL_WIDTH;
	     ballImg.src = ballImgSrc;
	     return ballImg;
    }
}
