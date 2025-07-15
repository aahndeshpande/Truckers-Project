import React, { useEffect } from 'react';
import {
    Box,
    Container,
    Grid,
    Paper,
    Typography,
    Card,
    CardContent,
    CardMedia,
    Button,
} from '@mui/material';
import { useAppSelector } from '../store/hooks';
import { selectCurrentUser } from '../store/reducers/auth';
import { useNavigate } from 'react-router-dom';

const Dashboard: React.FC = () => {
    const user = useAppSelector(selectCurrentUser);
    const navigate = useNavigate();

    const handleNavigate = (path: string) => {
        navigate(path);
    };

    return (
        <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
            <Grid container spacing={3}>
                {/* Welcome Card */}
                <Grid item xs={12}>
                    <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
                        <Typography component="h1" variant="h6" color="primary" gutterBottom>
                            Welcome, {user?.username}!
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            Your Food Truck Community Dashboard
                        </Typography>
                    </Paper>
                </Grid>

                {/* Quick Actions */}
                <Grid item xs={12} sm={6} md={3}>
                    <Card
                        sx={{
                            height: '100%',
                            display: 'flex',
                            flexDirection: 'column',
                            cursor: 'pointer',
                            '&:hover': {
                                boxShadow: 8,
                            },
                        }}
                        onClick={() => handleNavigate('/communities')}
                    >
                        <CardMedia
                            component="img"
                            sx={{
                                pt: '56.25%',
                                backgroundSize: 'cover',
                                backgroundImage: 'url(/community.jpg)',
                            }}
                            title="Communities"
                        />
                        <CardContent sx={{ flexGrow: 1 }}>
                            <Typography gutterBottom variant="h5" component="h2">
                                Communities
                            </Typography>
                            <Typography>
                                Find and join local food truck communities
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item xs={12} sm={6} md={3}>
                    <Card
                        sx={{
                            height: '100%',
                            display: 'flex',
                            flexDirection: 'column',
                            cursor: 'pointer',
                            '&:hover': {
                                boxShadow: 8,
                            },
                        }}
                        onClick={() => handleNavigate('/events')}
                    >
                        <CardMedia
                            component="img"
                            sx={{
                                pt: '56.25%',
                                backgroundSize: 'cover',
                                backgroundImage: 'url(/events.jpg)',
                            }}
                            title="Events"
                        />
                        <CardContent sx={{ flexGrow: 1 }}>
                            <Typography gutterBottom variant="h5" component="h2">
                                Events
                            </Typography>
                            <Typography>
                                Discover upcoming food truck events
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item xs={12} sm={6} md={3}>
                    <Card
                        sx={{
                            height: '100%',
                            display: 'flex',
                            flexDirection: 'column',
                            cursor: 'pointer',
                            '&:hover': {
                                boxShadow: 8,
                            },
                        }}
                        onClick={() => handleNavigate('/food-trucks')}
                    >
                        <CardMedia
                            component="img"
                            sx={{
                                pt: '56.25%',
                                backgroundSize: 'cover',
                                backgroundImage: 'url(/food-trucks.jpg)',
                            }}
                            title="Food Trucks"
                        />
                        <CardContent sx={{ flexGrow: 1 }}>
                            <Typography gutterBottom variant="h5" component="h2">
                                Food Trucks
                            </Typography>
                            <Typography>
                                Explore food trucks in your area
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item xs={12} sm={6} md={3}>
                    <Card
                        sx={{
                            height: '100%',
                            display: 'flex',
                            flexDirection: 'column',
                            cursor: 'pointer',
                            '&:hover': {
                                boxShadow: 8,
                            },
                        }}
                        onClick={() => handleNavigate('/profile')}
                    >
                        <CardMedia
                            component="img"
                            sx={{
                                pt: '56.25%',
                                backgroundSize: 'cover',
                                backgroundImage: 'url(/profile.jpg)',
                            }}
                            title="Profile"
                        />
                        <CardContent sx={{ flexGrow: 1 }}>
                            <Typography gutterBottom variant="h5" component="h2">
                                Profile
                            </Typography>
                            <Typography>
                                Manage your account and preferences
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
        </Container>
    );
};

export default Dashboard;
