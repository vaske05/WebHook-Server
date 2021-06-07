import { combineReducers } from "redux";
import errorsReducer from "./errorsReducer";
import securityReducer from "./securityReducer";

export default combineReducers ({
    errors: errorsReducer,
    security: securityReducer
});