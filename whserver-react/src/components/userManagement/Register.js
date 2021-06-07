import React, {Component} from 'react';
import { createNewUser } from "../../actions/securityActions";
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import classNames from 'classnames';

class Register extends Component {

    constructor() {
        super();

        this.state = {
            username: "",
            fullName: "",
            password: "",
            confirmPassword: "",
            errors: {}
        };
    }

    componentDidMount() {
        if(this.props.security.isAuthenticated) {
            this.props.history.push("/dashboard");
        }
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
    }

    onSubmit = (e) => {
        e.preventDefault();
        const newUser = {
            username: this.state.username,
            fullName: this.state.fullName,
            password: this.state.password,
            confirmPassword: this.state.confirmPassword,
        }
        this.props.createNewUser(newUser, this.props.history);
    }

    componentWillReceiveProps(nextProps) {
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    render() {
        const {errors} = this.state;
        return (
            <div className="register">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h1 className="display-4 text-center">Sign Up</h1>
                            <p className="lead text-center">Create your Account</p>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text"
                                           className={classNames("form-control form-control-lg", {
                                               "is-invalid": errors.fullName
                                           })}
                                           placeholder="Full Name"
                                           name="fullName"
                                           value={this.state.fullName}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.fullName && (<div className="invalid-feedback">{errors.fullName}</div>)
                                    }
                                </div>
                                <div className="form-group">
                                    <input type="text"
                                           className={classNames("form-control form-control-lg", {
                                               "is-invalid": errors.username
                                           })}
                                           placeholder="Email Address (Username)"
                                           name="username"
                                           value={this.state.username}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.username && (<div className="invalid-feedback">{errors.username}</div>)
                                    }

                                </div>
                                <div className="form-group">
                                    <input type="password"
                                           className={classNames("form-control form-control-lg", {
                                               "is-invalid": errors.password
                                           })}
                                           placeholder="Password"
                                           name="password"
                                           value={this.state.password}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.password && (<div className="invalid-feedback">{errors.password}</div>)
                                    }
                                </div>
                                <div className="form-group">
                                    <input type="password"
                                           className={classNames("form-control form-control-lg", {
                                               "is-invalid": errors.confirmPassword
                                           })}
                                           placeholder="Confirm Password"
                                           name="confirmPassword"
                                           value={this.state.confirmPassword}
                                           onChange={this.onChange}
                                    />
                                    {
                                        errors.confirmPassword && (<div className="invalid-feedback">{errors.confirmPassword}</div>)
                                    }
                                </div>
                                <input type="submit" className="btn btn-info btn-block mt-4 font-weight-bold" value="Sign Up"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Register.propTypes = {
    createNewUser: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
});

export default connect(mapStateToProps, {createNewUser})(Register);