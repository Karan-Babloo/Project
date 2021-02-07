import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";


export default class Tutorial extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.getTutorial = this.getTutorial.bind(this);
    this.updatePublished = this.updatePublished.bind(this);
    this.updateTutorial = this.updateTutorial.bind(this);
    this.deleteTutorial = this.deleteTutorial.bind(this);
    this.insertTutorial = this.insertTutorial.bind(this);
    
    this.state = {
      currentTutorial: {
        createDate: "",
        productName:"",
        productSku:""
      },
      message: ""
    };
  }

  componentDidMount() {
    const exists = this.props.match.params.id
    console.log('check exists', exists);
    if(exists !== 'new'){
      this.getTutorial(this.props.match.params.id);       
    } else {
      // new
    }
      
  }

  onChangeTitle(e) {
    const title = e.target.value;
    console.log('check sku', title);
    this.setState(function(prevState) {
      return {
        currentTutorial: {
          ...prevState.currentTutorial,
          productSku: title
        }
      };
    });
  }

  onChangeDescription(e) {
    const description = e.target.value;
    console.log('check description', description);
    this.setState(prevState => ({
      currentTutorial: {
        ...prevState.currentTutorial,
        productName: description
      }
    }));
  }

  getTutorial(id) {
    TutorialDataService.get(id)
      .then(response => {
        this.setState({
          currentTutorial: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updatePublished(status) {
    var data = {
      id: this.state.currentTutorial.id,
      title: this.state.currentTutorial.title,
      description: this.state.currentTutorial.description,
      published: status
    };

    TutorialDataService.update(this.state.currentTutorial.id, data)
      .then(response => {
        this.setState(prevState => ({
          currentTutorial: {
            ...prevState.currentTutorial,
            published: status
          }
        }));
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updateTutorial() {
    const body = {
      "id":this.state.currentTutorial.id,
      "productSku":this.state.currentTutorial.productSku,
      "productName":this.state.currentTutorial.productName
    }
    TutorialDataService.update(
      this.state.currentTutorial.id,
      body
    ).then(response => {
        console.log(response.data);
        this.setState({
          message: "The tutorial was updated successfully!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  deleteTutorial() {    
    TutorialDataService.delete(this.state.currentTutorial.id)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/tutorials')
      })
      .catch(e => {
        console.log(e);
      });
  }

  insertTutorial() {    
    const body = {
      "productSku":this.state.currentTutorial.productSku,
      "productName":this.state.currentTutorial.productName
    }
    console.log('here is the body', body);
    TutorialDataService.insert(body)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/tutorials')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentTutorial } = this.state;
    const renderDeletUpdateButton = ()=>{
        return <div>
        <button
        className="badge badge-info mr-info mr-2"
        onClick={this.insertTutorial}
      >
        Insert
      </button>

      <button
        type="submit"
        className="badge badge-success mr-2"
        onClick={this.updateTutorial}
      >
        Update
      </button>

      <button
        className="badge badge-danger"
        onClick={this.deleteTutorial}
      >
        Delete
      </button>
      </div>
    }
    return (
      <div>
        {currentTutorial ? (
          <div className="edit-form">
            <h4>Product Details</h4>
            <form>
              <div className="form-group">
                <label htmlFor="title">SKU</label>
                <input
                  type="text"
                  className="form-control"
                  id="title"
                  value={currentTutorial.productSku}
                  onChange={this.onChangeTitle}
                />
              </div>
              <div className="form-group">
                <label htmlFor="description">Name</label>
                <input
                  type="text"
                  className="form-control"
                  id="description"
                  value={currentTutorial.productName}
                  onChange={this.onChangeDescription}
                />
              </div>
              </form>
            {renderDeletUpdateButton()}
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Tutorial...</p>
          </div>
        )}
      </div>
    );
  }
}
