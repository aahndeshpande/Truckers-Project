import React, { useEffect, useState } from 'react';
import {
    Box,
    Container,
    Paper,
    Typography,
    Button,
    TextField,
    Card,
    CardContent,
    CardMedia,
    CircularProgress,
    Avatar,
    Tabs,
    Tab,
    Grid,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import apiClient from '../api/apiClient';
import { User } from '../types/api';

const Profile: React.FC = () => {
    const [activeTab, setActiveTab] = useState(0);
    const [profile, setProfile] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);
    const dispatch = useAppDispatch();
    const navigate = useNavigate();
    const user = useAppSelector(selectCurrentUser);

    const handleTabChange = (_: React.SyntheticEvent, newValue: number) => {
        setActiveTab(newValue);
    };

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await apiClient.get<User>(`/users/${user?.id}`);
                setProfile(response.data);
            } catch (error) {
                console.error('Error fetching profile:', error);
            } finally {
                setLoading(false);
            }
        };

        if (user) {
            fetchProfile();
        }
    }, [user]);

    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
                <Typography component="h1" variant="h4" color="primary" gutterBottom>
                    Profile
                </Typography>
            </Box>

            <Paper sx={{ p: 3 }}>
                <Grid container spacing={3}>
                    <Grid item xs={12} md={4}>
                        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                            <Avatar
                                sx={{
                                    width: 150,
                                    height: 150,
                                    mb: 2,
                                    bgcolor: 'primary.main',
                                }}
                            >
                                {profile?.username?.[0] || 'U'}
                            </Avatar>
                            <Typography variant="h5" component="h2">
                                {profile?.username}
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                {profile?.email}
                            </Typography>
                            <Button
                                variant="contained"
                                color="primary"
                                sx={{ mt: 2 }}
                                onClick={() => navigate('/profile/edit')}
                            >
                                Edit Profile
                            </Button>
                        </Box>
                    </Grid>
                    <Grid item xs={12} md={8}>
                        <Tabs value={activeTab} onChange={handleTabChange}>
                            <Tab label="My Communities" />
                            <Tab label="My Events" />
                            <Tab label="My Food Trucks" />
                            <Tab label="Settings" />
                        </Tabs>

                        <Box sx={{ mt: 3 }}>
                            {activeTab === 0 && (
                                <Box>
                                    <Typography variant="h6" gutterBottom>
                                        My Communities
                                    </Typography>
                                    {/* Add communities list here */}
                                </Box>
                            )}
                            {activeTab === 1 && (
                                <Box>
                                    <Typography variant="h6" gutterBottom>
                                        My Events
                                    </Typography>
                                    {/* Add events list here */}
                                </Box>
                            )}
                            {activeTab === 2 && (
                                <Box>
                                    <Typography variant="h6" gutterBottom>
                                        My Food Trucks
                                    </Typography>
                                    {/* Add food trucks list here */}
                                </Box>
                            )}
                            {activeTab === 3 && (
                                <Box>
                                    <Typography variant="h6" gutterBottom>
                                        Settings
                                    </Typography>
                                    {/* Add settings form here */}
                                </Box>
                            )}
                        </Box>
                    </Grid>
                </Grid>
            </Paper>
        </Container>
    );
};

export default Profile;
