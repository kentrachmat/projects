
export default class Mobile {

  constructor(x, y, picture, width, stepX=0, stepY=0){
    this.image = this.createImage(picture);
    this._x = x;
    this._y = y;
    this.deltaX = stepX;
    this.deltaY = stepY;
    this._width = width;
    this.destroyed = false;
  }

  draw(context){
    context.drawImage(this.image, this._x, this._y);
  }

  move(){
    this._x += this.deltaX;
    this._y += this.deltaY;
  }

  isDestroyed(){
    return this.destroyed;
  }

  isIn(canvas){
    return cmp(0, this._x, canvas.width) && cmp(0, this._y, canvas.height);
  }

  createImage(picture) {
     const pic = new Image();
     pic.src = picture;
     pic.width = this._width;
     return pic;
  }
}

const cmp = (x, y, z) => {
  return x <= y && y <= z;
}
