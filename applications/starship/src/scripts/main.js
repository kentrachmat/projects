
// importation de l'instance de Game créée dans Game.js
import Game from './game.js';


// mise en place de l'action des clics sur les boutons + les gestionnaires du clavier pour contrôler le starship
const init = () => {
  const canvas = document.getElementById("stars");
  const infinityEnemy = document.getElementById("flotteSoucoupes");
  const addEnemy = document.getElementById("nouvelleSoucoupe");


  const game = new Game(canvas);

  window.addEventListener('keydown', game.keyDownActionHandler.bind(game));
  window.addEventListener('keyup', game.keyUpActionHandler.bind(game));
  addEnemy.addEventListener("click", () => game.addEnemy() );
  infinityEnemy.addEventListener("click", () => game.multipleShip() );
  game.moveAndDraw();
}

window.addEventListener("load",init);

console.log('le bundle a été généré');
