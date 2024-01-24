import React from 'react';
import PropTypes from 'prop-types';

import '../assets/style/addtask.css';

/*
 define root component
*/
export default class AddTask extends React.Component {
  constructor(props) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick() {
    const newData = {
                    description : this.refs.description.value,
                    duration : parseInt(this.refs.duration.value)
                   };
    if(this.refs.description.value != "" && this.refs.duration.value != ""){
      this.props.addData(newData);
    }
    else{
      alert("Veuillez remplir la description et la valeur de la durée");
    }
    this.clearFields();
  }
  clearFields() {
    this.refs.description.value = '';
    this.refs.duration.value = '';
  }
  render() {
    return(
        <div className="addTask">
          <input
            type="text" placeholder="description"
            ref="description"
          />
          <input
            type= "number" min="0" placeholder="duration"
            ref="duration"
          />mn
        <button onClick ={this.handleClick}>Add</button>
        </div>
    );
  }

  static propTypes = {
    addData : PropTypes.func
  }
}
