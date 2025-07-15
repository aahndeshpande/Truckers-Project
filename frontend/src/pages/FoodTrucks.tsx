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
    Rating,
} from '@mui/material';
import { Search as SearchIcon } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import apiClient from '../api/apiClient';
import { FoodTruck } from '../types/api';

const FoodTrucks: React.FC = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [foodTrucks, setFoodTrucks] = useState<FoodTruck[]>([]);
    const [loading, setLoading] = useState(true);
    const dispatch = useAppDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchFoodTrucks = async () => {
            try {
                const response = await apiClient.get<FoodTruck[]>('/food-trucks');
                setFoodTrucks(response.data);
            } catch (error) {
                console.error('Error fetching food trucks:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchFoodTrucks();
    }, []);

    const handleSearch = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await apiClient.get<FoodTruck[]>(`/food-trucks/search?q=${searchQuery}`);
            setFoodTrucks(response.data);
        } catch (error) {
            console.error('Error searching food trucks:', error);
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
                    Food Trucks
                </Typography>
                <Button variant="contained" color="primary" onClick={() => navigate('/food-trucks/new')}>
                    Add Food Truck
                </Button>
            </Box>

            <Box sx={{ mb: 4 }}>
                <form onSubmit={handleSearch}>
                    <TextField
                        fullWidth
                        placeholder="Search food trucks..."
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
                {foodTrucks.map((foodTruck) => (
                    <Grid item xs={12} sm={6} md={4} key={foodTruck.id}>
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
                            onClick={() => navigate(`/food-trucks/${foodTruck.id}`)}
                        >
                            <CardMedia
                                component="img"
                                sx={{
                                    pt: '56.25%',
                                    backgroundSize: 'cover',
                                    backgroundImage: `url(${foodTruck.imageUrl})`,
                                }}
                                title={foodTruck.name}
                            />
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography gutterBottom variant="h5" component="h2">
                                    {foodTruck.name}
                                </Typography>
                                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                                    {foodTruck.description}
                                </Typography>
                                <Rating
                                    value={foodTruck.averageRating}
                                    readOnly
                                    size="small"
                                    sx={{ mb: 2 }}
                                />
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Location:</strong> {foodTruck.location}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Menu Items:</strong> {foodTruck.menuItems.length}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    <strong>Events:</strong> {foodTruck.events.length}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Container>
    );
};

export default FoodTrucks;
