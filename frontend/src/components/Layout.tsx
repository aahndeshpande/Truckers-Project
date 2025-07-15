import React from 'react';
import { useNavigate } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, IconButton, Box } from '@mui/material';
import { AccountCircle, Menu as MenuIcon } from '@mui/icons-material';
import { useAppSelector } from '../store/hooks';
import { selectAuth } from '../store/reducers/auth';

const Layout: React.FC = ({ children }) => {
    const navigate = useNavigate();
    const auth = useAppSelector(selectAuth);

    const handleLogout = () => {
        // Dispatch logout action
        navigate('/login');
    };

    return (
        <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Food Truck Community
                    </Typography>
                    {auth.user ? (
                        <Box>
                            <Button color="inherit" onClick={handleLogout}>
                                Logout
                            </Button>
                            <IconButton color="inherit">
                                <AccountCircle />
                            </IconButton>
                        </Box>
                    ) : (
                        <Box>
                            <Button color="inherit" onClick={() => navigate('/login')}>
                                Login
                            </Button>
                            <Button color="inherit" onClick={() => navigate('/register')}>
                                Register
                            </Button>
                        </Box>
                    )}
                </Toolbar>
            </AppBar>
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                {children}
            </Box>
        </Box>
    );
};

export default Layout;
