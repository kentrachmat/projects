import React from 'react';

import '../assets/style/tasklist.css';

import Done from './done.jsx';

/*
 define root component
*/
export default class DoneTask extends React.Component {
  constructor(props) {
    super(props);
    this.state = {closed : true};
    this.closeComponent = this.closeComponent.bind(this);
  }

  closeComponent() {
    this.setState( (prevstate) => ( {closed : ! prevstate.closed} ) );
  }

  render() {
    return (
      <div className="tasklist">
        <h3>Tâches terminées</h3>
        il y a {this.props.done.length} tâches terminées. Pour une durée de {this.props.done.map(item => item.duration).reduce((a,b) => a+b, 0)} mn.<br/>

        <button onClick= { this.closeComponent }>
          { this.state.closed ? '+('+this.props.done.length+')'  : '-' }
        </button>
        {this.buildDoneTask()}
      </div>
    );
  }

  buildDoneTask() {
    if (this.state.closed)
        return null;
    else
        return (this.props.done.map(item =>
                <Done
                    {...item} key={item.id}
                />)
              );
  }
}
