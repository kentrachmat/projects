


import React from 'react';

import '../assets/style/task.css';


/*
 define root component
*/
export default class DoneTask extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="task">
      {this.props.description} ({this.props.duration}mn) (priorité : {this.props.priority})
      </div>
    );
  }
}
