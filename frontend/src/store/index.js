import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';
import foodTruckReducer from './slices/foodTruckSlice';
import menuReducer from './slices/menuSlice';
import reviewReducer from './slices/reviewSlice';
import userReducer from './slices/userSlice';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import thunk from 'redux-thunk';

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['auth', 'user'],
};

const rootReducer = {
  auth: authReducer,
  foodTrucks: foodTruckReducer,
  menu: menuReducer,
  reviews: reviewReducer,
  user: userReducer,
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: [thunk],
});

export const persistor = persistStore(store);
