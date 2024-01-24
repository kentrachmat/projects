import Animation from './animation';

/* TYPE AnimationWithObstacle */
export default class AnimationWithObstacle extends Animation {

  constructor(canvas, obstacle) {
    super(canvas);
    this.obstacle = obstacle;
  }

  moveAndDraw(){
    this.context.clearRect(0,0, this.canvas.width, this.canvas.height);
    this.obstacle.draw(this.context);
    this.obstacle.move(this.canvas);
    this.ball = this.ball.filter(item => !item.collisionWith(this.obstacle));
    this.ball.forEach(item => item.draw(this.context));
    this.ball.forEach(item => item.move(this.context));
    this.animation = window.requestAnimationFrame(this.moveAndDraw.bind(this));
  }

  keyDownActionHandler(event) {
     switch (event.key) {
         case "ArrowLeft":
         case "Left":
             this.obstacle.moveLeft();
             break;
         case "ArrowRight":
         case "Right":
             this.obstacle.moveRight();
             break;
         case "ArrowDown":
         case "Down":
             this.obstacle.moveDown();
             break;
         case "ArrowUp":
         case "Up":
             this.obstacle.moveUp();
             break;
         default: return;
     }
     event.preventDefault();
  }

  keyUpActionHandler(event) {
      switch (event.key) {
          case "ArrowLeft":
          case "Left":
          case "ArrowRight":
          case "Right":
          case "ArrowDown":
          case "Down":
          case "ArrowUp":
          case "Up":
              this.obstacle.stopMoving();
              break;
          default: return;
      }
      event.preventDefault();
  }
}
