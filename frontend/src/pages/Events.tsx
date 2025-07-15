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
import { Event } from '../types/api';

const Events: React.FC = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [events, setEvents] = useState<Event[]>([]);
    const [loading, setLoading] = useState(true);
    const dispatch = useAppDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchEvents = async () => {
            try {
                const response = await apiClient.get<Event[]>('/events');
                setEvents(response.data);
            } catch (error) {
                console.error('Error fetching events:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchEvents();
    }, []);

    const handleSearch = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await apiClient.get<Event[]>(`/events/search?q=${searchQuery}`);
            setEvents(response.data);
        } catch (error) {
            console.error('Error searching events:', error);
        }
    };

    const handleRegisterForEvent = async (eventId: number) => {
        try {
            await apiClient.post(`/event-participants/register?eventId=${eventId}`);
            // Refresh events list
            const response = await apiClient.get<Event[]>('/events');
            setEvents(response.data);
        } catch (error) {
            console.error('Error registering for event:', error);
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
                    Events
                </Typography>
                <Button variant="contained" color="primary" onClick={() => navigate('/events/new')}>
                    Create Event
                </Button>
            </Box>

            <Box sx={{ mb: 4 }}>
                <form onSubmit={handleSearch}>
                    <TextField
                        fullWidth
                        placeholder="Search events..."
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
                {events.map((event) => (
                    <Grid item xs={12} sm={6} md={4} key={event.id}>
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
                            onClick={() => navigate(`/events/${event.id}`)}
                        >
                            <CardMedia
                                component="img"
                                sx={{
                                    pt: '56.25%',
                                    backgroundSize: 'cover',
                                    backgroundImage: `url(${event.imageUrl})`,
                                }}
                                title={event.name}
                            />
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography gutterBottom variant="h5" component="h2">
                                    {event.name}
                                </Typography>
                                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                                    {event.description}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Location:</strong> {event.location}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Date:</strong> {new Date(event.startTime).toLocaleDateString()}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Time:</strong> {new Date(event.startTime).toLocaleTimeString()}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Spots Left:</strong> {event.capacity - event.participants.length}
                                </Typography>
                                <Button
                                    variant="contained"
                                    color="primary"
                                    size="small"
                                    sx={{ mt: 2 }}
                                    onClick={(e) => {
                                        e.stopPropagation();
                                        handleRegisterForEvent(event.id);
                                    }}
                                >
                                    Register for Event
                                </Button>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default Events;
