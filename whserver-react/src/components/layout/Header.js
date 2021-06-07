import React, { Component } from "react";
import {Link} from "react-router-dom";
import PropTypes from 'prop-types';
import {connect} from "react-redux";
import {logout} from "../../actions/securityActions";

class Header extends Component {

  constructor(props) {
    super(props);
    this.state = {
      remainingTime: 0,
      timerOn: false,
      intervalId: ""
    }
  }

  logout() {
    this.setState({timerOn: false});
    clearInterval(this.state.intervalId);
    this.props.logout(this.props.history);
  }

  formatTime = (inputSeconds) => {
    let hours   = Math.floor(inputSeconds / 3600);
    let minutes = Math.floor((inputSeconds - (hours * 3600)) / 60);
    let seconds = inputSeconds - (hours * 3600) - (minutes * 60);

    if (hours   < 10) {hours   = "0"+hours;}
    if (minutes < 10) {minutes = "0"+minutes;}
    if (seconds < 10) {seconds = "0"+seconds;}
    return hours + 'h:'+minutes + 'm:'+seconds + 's';
  }

  startTimer = (userTime) => {
    let remainingTime;

    const intervalId = setInterval( () => {
      remainingTime = userTime - Date.now()/1000;
      remainingTime = Number(remainingTime).toFixed(0);

      if(remainingTime <= 0 || isNaN(remainingTime)) {
        this.setState({timerOn:false});
        clearInterval(intervalId);
      }
      this.setState({remainingTime: this.formatTime(remainingTime)});

    }, 1000)
    this.setState({intervalId: intervalId});
  }

  render() {
    const { user, isAuthenticated } = this.props.security;

    if(!this.state.timerOn && isAuthenticated ) {
      this.startTimer(user.exp);
      this.setState({timerOn: true});
    }

    const userIsAuthenticated = (
        <div className="collapse navbar-collapse" id="mobile-nav">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/dashboard">
              Dashboard
              </Link>
            </li>
            <li className="nav-item cursorPointer">
              <i className="nav-link normal-font-style" onClick={this.logout.bind(this)}>
                Logout
              </i>
            </li>
          </ul>

          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link className="nav-link " to="/dashboard">
                <i className="fas fa-user-circle mr-1" />
                {user.fullName}
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link " to="/dashboard">
                <i className="fas mr-1 fa-clock" > </i>
                <i>Your sessions ends in: {this.state.remainingTime}</i>
              </Link>
            </li>
          </ul>
        </div>
    )

    const userIsNotAuthenticated = (
        <div className="collapse navbar-collapse" id="mobile-nav">
          <Link to="/" className="navbar-brand">
            WebHook Server
          </Link>

          <ul className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link className="nav-link " to="/register">
                Sign Up
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/login">
                Log In
              </Link>
            </li>
          </ul>
        </div>
    )

    let headerLinks
    if(isAuthenticated) {
      headerLinks = userIsAuthenticated;
    } else {
      headerLinks = userIsNotAuthenticated;
    }

    return (
      <nav className="navbar navbar-expand-sm navbar-dark greenPrimary mb-4">
        <div className="container">
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#mobile-nav"
          >
            <span className="navbar-toggler-icon" />
          </button>

          {headerLinks}

        </div>
      </nav>
    );
  }
}

Header.propTypes ={
  logout: PropTypes.func.isRequired,
  security: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  security: state.security
})

export default connect(mapStateToProps, {logout})(Header) ;
