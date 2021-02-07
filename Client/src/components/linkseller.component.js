import React, { Component } from "react";

export default class LinkSeller extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: null,
      title: "",
      description: "",
      published: false,
      sellerName: "",
      inventoryAmount: 0,

      submitted: false
    };
  }


  render() {
    return (
      <div>
        <h4>You get the point here we link products with sellers <br></br><br></br>and set the price per product per seller</h4>
        <br></br>
        <br></br>
        <h2> API under construction</h2>
      </div>
    );
  }
}
