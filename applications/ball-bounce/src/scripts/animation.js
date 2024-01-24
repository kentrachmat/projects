import Ball from './ball';

/* TYPE Animation */
export default class Animation {

  constructor(canvas){
    this.context = canvas.getContext('2d');
    this.ball = new Array();
    this.animation = null;
    this.canvas = canvas;
  }

  add(){
    let x = Math.floor(Math.random() * (this.canvas.width - 31) + 1);
    let y = Math.floor(Math.random() * (this.canvas.height - 31) + 1);
    let deltaX = Math.floor(Math.random() * (10) - 5);
    let deltaY = Math.floor(Math.random() * (10) - 5);
    this.ball.push(new Ball(x, y, deltaX, deltaY));
  }

  moveAndDraw(){
    this.context.clearRect(0,0, this.canvas.width, this.canvas.height);
    this.ball.map(item => item.draw(this.context)); // ou forEach
    this.ball.map(item => item.move(this.context)); // ou forEach
    this.animation = window.requestAnimationFrame(this.moveAndDraw.bind(this));
  }

  /* start the animation or stop it if previously running */
  startAndStop() {
    if(this.animation === null){
      window.requestAnimationFrame(this.moveAndDraw.bind(this));
      document.getElementById("stopStartBall").textContent = "Stop";
    }
    else{
      window.cancelAnimationFrame(this.animation);
      this.animation = null;
      document.getElementById("stopStartBall").textContent = "Start";
    }
  }
}
