import {SET_CURRENT_USER} from "../actions/types";
import {isEmpty} from "../helpers";


const initial_state = {
    user: {},
    isAuthenticated: false
}

const isAuthenticated = payload => {
    return !isEmpty(payload);
}

export default function (state=initial_state, action) {
    switch (action.type) {
        case SET_CURRENT_USER:
            return {
                ...state,
                isAuthenticated: isAuthenticated(action.payload),
                user: action.payload
            };

        default:
            return state;
    }
}