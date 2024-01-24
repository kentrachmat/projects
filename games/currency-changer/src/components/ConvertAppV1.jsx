import React from 'react';

import '../assets/style/app.css';

import Currency from './Currency.jsx';

/*
 define root component
*/
export default class ConvertAppV1 extends React.Component {
    constructor(props) {
    super(props);
    this.val = React.createRef();
    this.changeValue = this.changeValue.bind(this);
    this.state = {value : "1", currencies: []};
    }

    async componentDidMount() {
      const data = await simulateFetch('http://source.of.data/currencies');
      this.setState({ currencies : data });
    }

    changeValue(){
      this.setState({value : parseFloat(this.refs.val.value)});
    }


    render() {
      return (
        <div className="app">
        <input
           type="text" placeholder=".." ref="val"
        />
        <button onClick={this.changeValue}> OK </button>
        <Currency currencies={this.state.currencies } euro={this.state.value}/>
        </div>
        );
    }
}

import currencyData from '../data/currencies.js';
const simulateFetch = async mockURL => {
    await setTimeout( () => console.log(`simulate fetching data from ${mockURL}`), 10);
    return currencyData;
}
