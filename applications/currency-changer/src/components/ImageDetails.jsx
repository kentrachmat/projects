import React from 'react';

/*
 define root component
*/
export default class ImageDetails extends React.Component {
  constructor(props) {
    super(props);
    this.state = { filterValue : '' };
    this.filterValueChanged = this.filterValueChanged.bind(this);
  }

  filterValueChanged(event){
    this.props.filterChanged(event.target.value);
    this.setState( { filterValue : event.target.value } );
  }

  render() {
    return (
      <div id="details">
      <img src = {this.props.image} alt = {this.props.texte} title = {this.props.texte} />
      <div className="legende">{this.props.texte}</div>
      <input
         id="filtre" type="text" placeholder="filtre image..."
         value= {this.state.filterValue}
         onChange = {this.filterValueChanged}
      />
      </div>
    );
  }
}
