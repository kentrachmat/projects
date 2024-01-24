import React from 'react';

import '../assets/style/app.css';

import Currency from './Currency.jsx';

/*
 define root component
*/
export default class ConvertAppV2 extends React.Component {
    constructor(props) {
    super(props);
    this.changeValue = this.changeValue.bind(this);
    this.state = {value : "1", currencies: []};
    }

    async componentDidMount() {
      const data = await simulateFetch('http://source.of.data/currencies');
      this.setState({ currencies : data });
    }

    changeValue(event){
      this.setState({value : parseFloat(event.target.value)});
    }


    render() {
      return (
        <div className="app">
        <input
           type="text" value = {this.state.value ? this.state.value : "1"} onChange = {this.changeValue}
        />
        <Currency currencies = {this.state.currencies } euro = {this.state.value ? this.state.value : 1}/>
        </div>
        );
    }
}

import currencyData from '../data/currencies.js';
const simulateFetch = async mockURL => {
    await setTimeout( () => console.log(`simulate fetching data from ${mockURL}`), 10);
    return currencyData;
}
