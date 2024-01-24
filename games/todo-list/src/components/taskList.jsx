import React from 'react';
import PropTypes from 'prop-types';

import '../assets/style/tasklist.css';

import Task from './task.jsx';

/*
 define root component
*/
export default class TaskList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {value : ""};
    this.changeValue = this.changeValue.bind(this);
  }

  changeValue(event){
    this.setState({value : event.target.value});
  }

  render() {
    const newList = this.props.todo.filter((item, index) => item.description.toLowerCase().includes(this.state.value.toLowerCase())).sort((a,b) => b.priority - a.priority)
                                   .map(item => <Task {...item} addEdit={this.props.addEdit} addDone={this.props.addDone} handleClick={this.props.handleClick} key={item.id} />);

    return (
      <div className="tasklist">
        <h3>Tâches en cours</h3>
        <input
           type="text" value = {this.state.value} placeholder="Input Filtre" onChange = {this.changeValue}
        /><br/>
        il y a {newList.length} tâches en cours. Pour une durée de {this.totalTime(newList)} mn.
        {newList}
      </div>
    );
  }

  totalTime(newList){
    return this.props.todo.filter(item => item.description.toLowerCase().includes(this.state.value.toLowerCase()))
                          .map(item => item.duration).reduce((a,b) => a+b, 0);
  }

  static propTypes = {
    todo : PropTypes.array.isRequired,
    addDone : PropTypes.func,
    handleClick : PropTypes.func
  }
}
