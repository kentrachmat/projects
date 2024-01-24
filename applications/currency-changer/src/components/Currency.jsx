import React from 'react';

import '../assets/style/currency.css';

/*
 define root component
*/
export default class Currency extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const currency = this.props.currencies.map( item => <div className="currency" key={item.symbol}>{(item.rate * this.props.euro).toFixed(2)} {item.symbol}</div>);
    return (
      <div id="currency">
      {currency}
      </div>
    );
  }
}
