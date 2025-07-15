import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { store, persistor } from './store';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import FoodTrucks from './pages/FoodTrucks';
import FoodTruckDetails from './pages/FoodTruckDetails';
import Profile from './pages/Profile';
import AddFoodTruck from './pages/AddFoodTruck';
import AddMenu from './pages/AddMenu';
import AddReview from './pages/AddReview';
import Search from './pages/Search';
import ErrorBoundary from './components/ErrorBoundary';
import ToastNotification from './components/ToastNotification';

const theme = createTheme({
  palette: {
    primary: {
      main: '#ff6b6b',
    },
    secondary: {
      main: '#4ecdc4',
    },
  },
});

function App() {
  return (
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <ThemeProvider theme={theme}>
          <CssBaseline />
          <ErrorBoundary>
            <ToastNotification />
            <Router>
              <Navbar />
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route 
                  path="/food-trucks" 
                  element={
                    <AuthGuard>
                      <FoodTrucks />
                    </AuthGuard>
                  }
                />
                <Route 
                  path="/food-trucks/:id" 
                  element={
                    <AuthGuard>
                      <FoodTruckDetails />
                    </AuthGuard>
                  }
                />
                <Route 
                  path="/profile" 
                  element={
                    <AuthGuard>
                      <Profile />
                    </AuthGuard>
                  }
                />
                <Route 
                  path="/add-food-truck" 
                  element={
                    <AuthGuard allowedRoles={['OWNER']}>
                      <AddFoodTruck />
                    </AuthGuard>
                  }
                />
                <Route 
                  path="/food-trucks/:id/add-menu" 
                  element={
                    <AuthGuard allowedRoles={['OWNER']}>
                      <AddMenu />
                    </AuthGuard>
                  }
                />
                <Route 
                  path="/food-trucks/:id/add-review" 
                  element={
                    <AuthGuard>
                      <AddReview />
                    </AuthGuard>
                  }
                />
                <Route path="/search" element={<Search />} />
              </Routes>
            </Router>
          </ErrorBoundary>
        </ThemeProvider>
      </PersistGate>
    </Provider>
  );
}

export default App;
