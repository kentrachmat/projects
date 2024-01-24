import React from 'react';

import '../assets/style/priorityLevel.css';

/*
 define root component
*/
export default class PriorityLevel extends React.Component {
  constructor(props) {
    super(props);
    this.changeColor = this.changeColor.bind(this);

  }

  changeColor(){
    this.props.handleClick(this.props.index, this.props.id);
  }

  render() {
    return (
      <div className={this.props.isActive ? 'level on' : 'level off'} onClick={this.changeColor}></div>
    );
  }
}
