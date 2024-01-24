import * as msg from "./messageConstants.js"

const nbValues = 12
const defaultValue = 1
const MIN_VALUE = 0
const MAX_VALUE = 10
const socket = io()
const connectionServ = (data) =>
  (document.getElementById("connectionServ").innerHTML = data)

socket.on(msg.WELCOME, () => connectionServ("connection with server done"))
window.setTimeout(() => socket.emit(msg.GREATINGS), 1000)
socket.on(msg.RECV_DATA, (data) => {
  let pop = removeData(myChart)
  addData(myChart, pop, data)
})
//socket.on(msg.RECV_DATA, (data) => console.log(data))

const allLabels = new Array(nbValues)
  .fill(defaultValue)
  .map((_, i) => String.fromCharCode("A".charCodeAt(0) + i))
//const allLabels = ["J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"]

// l'objet Chart
let myChart

const setup = () => {
  const ctxt = document.getElementById("myChart").getContext("2d")

  myChart = new Chart(ctxt, {
    type: "bar",
    data: {
      labels: allLabels,
      datasets: [
        {
          label: `mes ${nbValues} dernières données`,
          data: new Array(nbValues).fill(defaultValue),
          backgroundColor: "rgba(128,255,128,0.5)",
          borderColor: "rgba(0, 0, 0, 1)",
          borderWidth: 1,
        },
      ],
    },
    options: {
      scales: {
        y: {
          min: MIN_VALUE,
          max: MAX_VALUE,
        },
      },
    },
  })
}

window.addEventListener("DOMContentLoaded", setup)

function addData(chart, label, data) {
  chart.data.labels.unshift(label)
  chart.data.datasets.forEach((dataset) => {
    dataset.data.unshift(data)
  })
  chart.update()
}

function removeData(chart) {
  let pop = chart.data.labels.pop()
  chart.data.datasets.forEach((dataset) => {
    dataset.data.pop()
  })
  chart.update()
  return pop
}
