import './App.css';
import "bootstrap/dist/css/bootstrap.min.css"; // Bootstrap import

import {Provider} from "react-redux";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Header from "./components/layout/Header";
import Landing from "./components/layout/Landing";
import Register from "./components/userManagement/Register";
import store from "./store";
import jwt_decode from 'jwt-decode';
import {SET_CURRENT_USER} from "./actions/types";
import setJwtToken from "./securityUtils/setJwtToken";



/*
 * Set token and start timer logout when page gets reloaded
 */
const token = localStorage.getItem("jwtToken");
if(token) {
    setJwtToken(token);
    const decodedToken = jwt_decode(token);
    store.dispatch({
        type: SET_CURRENT_USER,
        payload: decodedToken
    });
    //startLogoutTimer(decodedToken.exp)(store.dispatch); TODO: check this
}

function App() {
  return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header/>

            { /* Public Routes */ }
            <Route exact path="/" component={Landing}/>
            <Route exact path="/register" component={Register}/>
            {/*<Route exact path="/login" component={Login}></Route>*/}

            {/*Private Routes*/}

          </div>
        </Router>
      </Provider>
  );
}

export default App;
