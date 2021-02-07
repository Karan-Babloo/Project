import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import { Link } from "react-router-dom";

export default class TutorialsList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveTutorials = this.retrieveTutorials.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTutorial = this.setActiveTutorial.bind(this);
    this.searchTitle = this.searchTitle.bind(this);

    this.state = {
      tutorials: [],
      currentTutorial: null,
      currentIndex: -1,
      searchTitle: ""
    };
  }

  componentDidMount() {
    this.retrieveTutorials();
  }

  onChangeSearchTitle(e) {
    const searchTitle = e.target.value;

    this.setState({
      searchTitle: searchTitle
    });
  }


  retrieveTutorials() {
    TutorialDataService.getAll()
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveTutorials();
    this.setState({
      currentTutorial: null,
      currentIndex: -1
    });
  }

  setActiveTutorial(tutorial, index) {
    this.setState({
      currentTutorial: tutorial,
      currentIndex: index
    });
  }

  searchTitle() {
    this.setState({
      currentTutorial: null,
      currentIndex: -1
    });

    TutorialDataService.findByTitle(this.state.searchTitle)
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { searchTitle, tutorials, currentTutorial, currentIndex } = this.state;
    return (
      <div className="list row">
        {/* <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by title"
              value={searchTitle}
              onChange={this.onChangeSearchTitle}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchTitle}
              >
                Search
              </button>
            </div>
          </div>
        </div> */}
        <div className="col-md-6">
          <h4>Products List</h4>

          <ul className="list-group">
            {tutorials &&
              tutorials.map((tutorial, index) => (

                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveTutorial(tutorial, index)}
                  key={index}
                >
                  {tutorial.productSku}
                </li>
              ))}
          </ul>
          <Link
            to={"/tutorials/new"}
            className="m-3 btn btn-sm btn-info"
          >
            Insert
          </Link>
        </div>
        <div className="col-md-6">
          {currentTutorial ? (
            <div>
              <h4>Product</h4>
              <div>
                <label>
                  <strong>ProductSku:</strong>
                </label>{" "}
                {currentTutorial.productSku}
              </div>
              <div>
                <label>
                  <strong>ProductID:</strong>
                </label>{" "}
                {currentTutorial.id}
              </div>
              <div>
                <label>
                  <strong>ProductName:</strong>
                </label>{" "}
                {currentTutorial.productName}
              </div>
              <div>
                <label>
                  <strong>CreateDate:</strong>
                </label>{" "}
                {currentTutorial.createDate}
              </div>
              <Link
                to={"/tutorials/" + currentTutorial.id}
                className="badge badge-warning"
              >
                Edit Product
              </Link>
              <div >
              <br></br>
              <br></br>
              <br></br>
              <h4>Available Sellers</h4>
              </div>
              <div>
                {currentTutorial.productSeller &&
                  currentTutorial.productSeller.map((sellerinfo, index) => (
                    
                    <div>
                      <div style={{color:'red'}}>
                        <label>
                        </label>{" "}
                        <h4>{"Seller - " + (index + 1 )}</h4>
                      </div>
                      <div>
                        <label>
                          <strong>SellerName:</strong>
                        </label>{" "}
                        {sellerinfo.seller.sellerName}
                      </div>
                      <div>
                        <label>
                          <strong>InventoryAmount:</strong>
                        </label>{" "}
                        {sellerinfo.seller.inventoryAmount}
                      </div>
                      <div>
                        <label>
                          <strong>Price:</strong>
                        </label>{" "}
                        {sellerinfo.price}
                      </div>
                    </div>
                  ))}
              </div>

            </div>
          ) : (
              <div>
                <br />
                <p>Please click on a Product...</p>
              </div>
            )}
        </div>
      </div>
    );
  }
}
