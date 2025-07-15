import { combineReducers } from '@reduxjs/toolkit';
import authReducer from './auth';

const rootReducer = combineReducers({
    auth: authReducer,
    // Add other reducers here
});

export default rootReducer;
