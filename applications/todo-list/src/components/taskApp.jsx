import React from 'react';

import '../assets/style/taskApp.css';

import TaskList from './taskList.jsx';
import AddTask from './addTask.jsx';
import DoneTask from './doneTask.jsx';

export default class TaskApp extends React.Component {
  constructor(props) {
    super(props);
    this.state = { todo : [], done : [] };
    this.addData = this.addData.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.addDone = this.addDone.bind(this);
    this.addEdit = this.addEdit.bind(this);
  }

  handleClick(key, id){
    const copyList = [...this.state.todo];
    const updatedList = copyList.find((item =>item.id == id));
    updatedList.priority = key;
    this.setState( { todo : copyList } );
  }

  addDone(id){
    const updatedDone = this.state.todo.find((item =>item.id == id));
    this.setState( { todo : this.state.todo.filter(item => item.id != id) } );
    this.setState( { done : [...this.state.done, updatedDone] } );
  }

  addEdit(id){
  const updatedDone = this.state.todo.find((item =>item.id == id));
  let desc = window.prompt("Saisissez la nouvelle description : ", updatedDone.description);
  let time = window.prompt("Saisissez la nouvelle duraion : ", updatedDone.duration);
  this.setState({ todo : this.state.todo.map((item) =>{
                    if(item.id == id){
                      item.description = desc==null ? updatedDone.description : desc;
                      item.duration = time==null ? updatedDone.duration : parseInt(time);
                    }
                    return item;
                })
              });
  }

  /*
  addLocalStorage = (item) => {
    let _items = this.state.items;
    _items.push(item);
    _items = Array.from(new Set(items));
    this.setState({items: _items});
  } */

  async componentDidMount() {
    const data = await simulateFetch('http://source.of.data/taskData');
    data.forEach(item => item.priority = 1);
    this.setState({ todo : data });
    /*let item = localStorage.getItem('datas');
    if(item){
      item = JSON.parse(item);
      item.forEach(this.addLocalStorage);
    }*/
  }

  addData(data) {
    const newId = `T${this.state.todo.length + 1}`;
    const newTodo = { ...data, id : newId, priority : 1 };
    this.setState ( prevState => ( { todo : [...prevState.todo, newTodo] } ) );
  }

  render() {
    return (
      <div className="taskApp">
        <AddTask addData = {this.addData}/>
        <TaskList addEdit={this.addEdit} addDone={this.addDone} handleClick={this.handleClick} todo={this.state.todo}/>
        <DoneTask done={this.state.done}/>
      </div>
    );
  }
}

import taskData from '../data/tasksData.js';
const simulateFetch = async mockURL => {
    await setTimeout( () => console.log(`simulate fetching data from ${mockURL}`), 10);
    return taskData;
}
