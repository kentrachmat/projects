import React from 'react';

import '../assets/style/doneButton.css';

/*
 define root component
*/
export default class DoneButton extends React.Component {
  constructor(props) {
    super(props);
    this.completeTask = this.completeTask.bind(this);
  }

  completeTask(){
    this.props.addDone(this.props.id);
  }

  render() {
    return (
      <div className="doneButton" onClick= { this.completeTask } >
          &#10003;
      </div>
    );
  }
}
