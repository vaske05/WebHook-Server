import React, {Component} from 'react';
import {Link} from "react-router-dom";
import PropTypes from 'prop-types';
import {connect} from "react-redux";

class Landing extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        if(this.props.security.isAuthenticated) {
            this.props.history.push("/dashboard");
        }
    }

    render() {
        return (
            <div className="landing">
                <div className="light-overlay landing-inner text-dark">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12 text-center">
                                <h1 className="display-3 mb-4">WebHok Server</h1>
                                <p className="lead">
                                    Create your account to create WebHooks for your API
                                </p>
                                <hr/>
                                <Link to="/register" className="btn btn-lg btn-secondary mr-2">
                                    Sign Up
                                </Link>
                                <Link to="login" className="btn btn-lg btn-success mr-2">
                                    Login
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Landing.propTypes = {
    security: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
   security: state.security
});

export default connect(mapStateToProps, {})(Landing);