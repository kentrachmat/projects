
import Starship from './starship';
import Saucer from './saucer';
import Shoot from './shoot';

const scoreX = document.getElementById("score");

export default class Game {
  constructor(canvas){
    this.context = canvas.getContext('2d');
    this.canva = canvas;
    this.vaisseau = new Starship(Math.floor(canvas.height/2));
    this.soucoupes = new Array();
    this.tir = new Array();
    this.score = 0;
    this.infinity = null;
  }

  moveAndDraw(){
    this.context.clearRect(0,0, this.canva.width, this.canva.height);
    this.vaisseau.move(this.canva);
    this.vaisseau.draw(this.context);

    this.tir.forEach(item => {
                              item.destroyHit(this.soucoupes);
                              if(item.destroyed)
                                this.score += 200;
                            });

    this.tir = this.tir.filter(item => !item.isDestroyed());
    this.soucoupes = this.soucoupes.filter(item => {
      if (!item.isIn(this.canva) && !item.isDestroyed())
        this.score -= 1000;
      return item.isIn(this.canva);
    });

    this.soucoupes.forEach(item => {
      item.move(this.canva);
      item.draw(this.context);
    });

    this.tir = this.tir.filter(item => item.isIn(this.canva));
    this.tir.forEach(item => {
      item.move(this.canva);
      item.draw(this.context);
    });

    scoreX.innerHTML = this.score;

    let val = Array.from(document.getElementsByName("back")).find(r => r.checked).value;
    let backg = document.getElementById("stars");

    if (val == "ciel")
      backg.style.backgroundImage = "url('../images/ciel-nocturne.png')";
    else if (val == "ville")
      backg.style.backgroundImage = "url('../images/dessert.png')";
    else if (val == "foret")
      backg.style.backgroundImage = "url('../images/plain.png')";

    window.requestAnimationFrame(this.moveAndDraw.bind(this));
  }

  addEnemy(){
    let y = Math.floor(Math.random() * (this.canva.height - 31) + 1);
    this.soucoupes.push(new Saucer(this.canva.width, y));
  }

  multipleShip() {
      if(this.infinity === null){
          this.infinity = window.setInterval(this.infinityShips.bind(this),750);
    } else{
      clearInterval(this.infinity);
      this.infinity = null;
    }
  }

infinityShips(){
  if(rdm()){
    this.addEnemy();
  }
}

  shoot(){
    this.tir.push(new Shoot(this.vaisseau._x+25, this.vaisseau._y+30));
  }

  keyDownActionHandler(event) {
     switch (event.key) {
         case "ArrowDown":
         case "Down":
             this.vaisseau.moveDown();
             break;
         case "ArrowUp":
         case "Up":
             this.vaisseau.moveUp();
             break;
        case " ":
             this.shoot();
             break;
         default: return;
     }
     event.preventDefault();
  }

  keyUpActionHandler(event) {
      switch (event.key) {
          case "ArrowDown":
          case "Down":
          case "ArrowUp":
          case "Up":
              this.vaisseau.stopMoving();
              break;
          default: return;
      }
      event.preventDefault();
  }
}

const rdm = ()=> Math.floor(Math.random()*2);
