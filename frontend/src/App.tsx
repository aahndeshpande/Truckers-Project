import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { store, persistor } from './store/store';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { ToastContainer } from 'react-toastify';

// Components
import Layout from './components/Layout';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import Communities from './pages/Communities';
import Events from './pages/Events';
import FoodTrucks from './pages/FoodTrucks';
import Profile from './pages/Profile';

// Theme
const theme = createTheme({
    palette: {
        primary: {
            main: '#1976d2',
        },
        secondary: {
            main: '#dc004e',
        },
    },
});

function App() {
    return (
        <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}>
                <ThemeProvider theme={theme}>
                    <CssBaseline />
                    <Router>
                        <Routes>
                            <Route path="/" element={<Layout />}> 
                                <Route index element={<Dashboard />} />
                                <Route path="communities" element={<Communities />} />
                                <Route path="events" element={<Events />} />
                                <Route path="food-trucks" element={<FoodTrucks />} />
                                <Route path="profile" element={<Profile />} />
                            </Route>
                            <Route path="/login" element={<Login />} />
                            <Route path="/register" element={<Register />} />
                        </Routes>
                    </Router>
                    <ToastContainer position="top-right" />
                </ThemeProvider>
            </PersistGate>
        </Provider>
    );
}

export default App;
