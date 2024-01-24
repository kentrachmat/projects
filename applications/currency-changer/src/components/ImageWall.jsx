import React from 'react';

/*
 define root component
*/
export default class ImageWall extends React.Component {
  constructor(props) {
    super(props);
    this.handleMouseOver = this.handleMouseOver.bind(this);
  }

  handleMouseOver(event) {
    this.props.imageChanged( event.target.src, event.target.alt );
  }

  render() {
    const images = this.props.images.filter(item => item.texte.toLowerCase().includes(this.props.filterText.toLowerCase()))
                                    .map( item => <img
                                                    src = { item.image } alt = { item.texte } title = { item.texte }
                                                    onMouseOver = { this.handleMouseOver }
                                                    key = { item.image }
                                                  />);
    return (
      <div id="mur">{images}</div>
    );
  }
}
