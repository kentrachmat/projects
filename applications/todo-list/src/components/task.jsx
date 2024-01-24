import React from 'react';

import '../assets/style/task.css';

import DoneButton from './doneButton.jsx';
import PriorityScale from './priorityScale.jsx';
import AddEdit from './addEdit.jsx';

/*
 define root component
*/
export default class Task extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="task">
        {this.props.description} ({this.props.duration}mn) 	&nbsp; 	&nbsp;
        <PriorityScale id={this.props.id} priority={this.props.priority} handleClick={this.props.handleClick}/>
        <DoneButton id={this.props.id} addDone={this.props.addDone}/>
        <AddEdit id={this.props.id} addEdit={this.props.addEdit}/>
      </div>
    );
  }
}
