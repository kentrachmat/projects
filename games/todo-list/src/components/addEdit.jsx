import React from 'react';

import '../assets/style/editButton.css';

/*
 define root component
*/
export default class AddEdit extends React.Component {
  constructor(props) {
    super(props);
    this.completeTask = this.completeTask.bind(this);
  }

  completeTask(){
    this.props.addEdit(this.props.id);
  }

  render() {
    return (
      <div className="editButton" onClick= { this.completeTask } >
          &#10227;
      </div>
    );
  }
}
