"use strict"

import Game from "./Game.js"
const delay = (ms) => new Promise((res) => setTimeout(res, ms))

const init = () => {
  const theField = document.getElementById("field")
  const theGame = new Game(theField)
  document
    .getElementById("start")
    .addEventListener("click", () => startGame(theGame))

  document
    .getElementById("restart")
    .addEventListener("click", () => location.reload())

  // document
  //   .getElementById("restart")
  //   .addEventListener("click", () => theGame.restart())
}

window.addEventListener("load", init)

// true iff game is started
let started = false
/** start and stop a game
 * @param {Game} theGame - the game to start and stop
 */
const startGame = (theGame) => {
  if (!started) {
    theGame.socket.on("startGame", function () {
      const delayFct = async () => {
        document.getElementById("score").innerHTML = `READY ??`
        await delay(1000)
        document.getElementById("score").innerHTML = `GO !!`
        await delay(1000)
        theGame.start()
      }
      delayFct()
    })
    theGame.init()
    document.getElementById("start").value = "STOP"
  } else {
    document.getElementById("start").value = "JOUER"
    theGame.stop()
  }
  started = !started
}
