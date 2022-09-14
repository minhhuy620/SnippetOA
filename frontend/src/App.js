import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import PostBody from "./components/snippet/snippet";
import { ToastContainer } from "react-toastify";
import "./assets/main.css";

class App extends Component {
  render() {
    return (
      <div className="container mt-3">
        <Route exact path={["/"]} component={PostBody} />
        <ToastContainer
          position="top-center"
          autoClose={900}
          newestOnTop={true}
        />
      </div>
    );
  }
}

export default App;
