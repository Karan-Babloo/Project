import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";

export default class AddTutorial extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.saveTutorial = this.saveTutorial.bind(this);
    this.newTutorial = this.newTutorial.bind(this);

    this.state = {
      id: null,
      title: "",
      description: "", 
      published: false,
      sellerName:"",
      inventoryAmount:0,

      submitted: false
    };
  }

  onChangeTitle(e) {
    this.setState({
      sellerName: e.target.value
    });
  }

  onChangeDescription(e) {
    this.setState({
      inventoryAmount: e.target.value
    });
  }

  saveTutorial() {
    var data = {
      "sellerName": this.state.sellerName,
      "inventoryAmount": this.state.inventoryAmount
    };

    TutorialDataService.createSeller(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          sellerName: response.data.sellerName,
          inventoryAmount: response.data.inventoryAmount,

          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newTutorial() {
    this.setState({
      id: null,
      title: "",
      description: "",
      published: false,

      submitted: false
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
            <button className="btn btn-success" onClick={this.newTutorial}>
              Add
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="title">Seller Name</label>
              <input
                type="text"
                className="form-control"
                id="title"
                required
                value={this.state.sellerName}
                onChange={this.onChangeTitle}
                name="title"
              />
            </div>

            <div className="form-group">
              <label htmlFor="description">Invertory Amount</label>
              <input
                type="text"
                className="form-control"
                id="description"
                required
                value={this.state.inventoryAmount}
                onChange={this.onChangeDescription}
                name="description"
              />
            </div>

            <button onClick={this.saveTutorial} className="btn btn-success">
              Submit
            </button>
          </div>
        )}
      </div>
    );
  }
}
