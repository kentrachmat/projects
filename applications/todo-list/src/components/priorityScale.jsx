import React from 'react';

import '../assets/style/priorityScale.css';

import PriorityLevel from './priorityLevel.jsx';

/*
 define root component
*/
export default class PriorityScale extends React.Component {

  static SIZE = 7;
  constructor(props) {
    super(props);
    this.state = {active: false};
  }

  render() {
    return (
      <div className="scale">
      {this.buildPriorityLevel()}
      ({this.props.priority})
      </div>
    );
  }

  buildPriorityLevel(){
    let tab =[];

    for (let i=1; i<PriorityScale.SIZE; i++){
      tab.push(<div key={i}><PriorityLevel key={i} id={this.props.id} index={i} isActive={i<=this.props.priority ? true : this.state.active} handleClick={ this.props.handleClick }/></div>);
    }
    return tab;
  }
}
