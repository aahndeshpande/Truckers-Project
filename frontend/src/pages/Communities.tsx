import React, { useEffect, useState } from 'react';
import {
    Box,
    Container,
    Grid,
    Paper,
    Typography,
    Button,
    TextField,
    IconButton,
    InputAdornment,
    Card,
    CardContent,
    CardMedia,
    CircularProgress,
} from '@mui/material';
import { Search as SearchIcon } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import apiClient from '../api/apiClient';
import { Community } from '../types/api';

const Communities: React.FC = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [communities, setCommunities] = useState<Community[]>([]);
    const [loading, setLoading] = useState(true);
    const dispatch = useAppDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCommunities = async () => {
            try {
                const response = await apiClient.get<Community[]>('/communities');
                setCommunities(response.data);
            } catch (error) {
                console.error('Error fetching communities:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchCommunities();
    }, []);

    const handleSearch = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await apiClient.get<Community[]>(`/communities/search?q=${searchQuery}`);
            setCommunities(response.data);
        } catch (error) {
            console.error('Error searching communities:', error);
        }
    };

    const handleJoinCommunity = async (communityId: number) => {
        try {
            await apiClient.post(`/community-members/join?communityId=${communityId}`);
            // Refresh communities list
            const response = await apiClient.get<Community[]>('/communities');
            setCommunities(response.data);
        } catch (error) {
            console.error('Error joining community:', error);
        }
    };

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
                    Communities
                </Typography>
                <Button variant="contained" color="primary" onClick={() => navigate('/communities/new')}>
                    Create Community
                </Button>
            </Box>

            <Box sx={{ mb: 4 }}>
                <form onSubmit={handleSearch}>
                    <TextField
                        fullWidth
                        placeholder="Search communities..."
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="end">
                                    <IconButton type="submit" edge="end">
                                        <SearchIcon />
                                    </IconButton>
                                </InputAdornment>
                            ),
                        }}
                    />
                </form>
            </Box>

            <Grid container spacing={3}>
                {communities.map((community) => (
                    <Grid item xs={12} sm={6} md={4} key={community.id}>
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
                            onClick={() => navigate(`/communities/${community.id}`)}
                        >
                            <CardMedia
                                component="img"
                                sx={{
                                    pt: '56.25%',
                                    backgroundSize: 'cover',
                                    backgroundImage: `url(${community.imageUrl})`,
                                }}
                                title={community.name}
                            />
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography gutterBottom variant="h5" component="h2">
                                    {community.name}
                                </Typography>
                                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                                    {community.description}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Location:</strong> {community.city}, {community.state}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Members:</strong> {community.members.length}
                                </Typography>
                                <Button
                                    variant="contained"
                                    color="primary"
                                    size="small"
                                    sx={{ mt: 2 }}
                                    onClick={(e) => {
                                        e.stopPropagation();
                                        handleJoinCommunity(community.id);
                                    }}
                                >
                                    Join Community
                                </Button>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default Communities;
