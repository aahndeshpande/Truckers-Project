import React, { useState, useEffect } from 'react';
import { Container, Grid, Card, CardContent, Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const FoodTrucks = () => {
  const navigate = useNavigate();
  const [foodTrucks, setFoodTrucks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchFoodTrucks = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/food-trucks');
        setFoodTrucks(response.data);
      } catch (err) {
        setError(err.response?.data?.message || 'Failed to fetch food trucks');
      } finally {
        setLoading(false);
      }
    };

    fetchFoodTrucks();
  }, []);

  if (loading) {
    return (
      <Container sx={{ mt: 4, mb: 4 }}>
        <Typography>Loading food trucks...</Typography>
      </Container>
    );
  }

  if (error) {
    return (
      <Container sx={{ mt: 4, mb: 4 }}>
        <Typography color="error">{error}</Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 4 }}>
        <Typography variant="h4">Food Trucks</Typography>
        <Button 
          variant="contained" 
          onClick={() => navigate('/add-food-truck')}
        >
          Add Food Truck
        </Button>
      </Box>

      <Grid container spacing={3}>
        {foodTrucks.map((truck) => (
          <Grid item xs={12} sm={6} md={4} key={truck.id}>
            <Card sx={{ height: '100%' }}>
              <CardContent>
                <Typography variant="h6" gutterBottom>
                  {truck.name}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {truck.cuisineType}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {truck.description}
                </Typography>
                <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 2 }}>
                  <Button 
                    variant="outlined" 
                    size="small" 
                    onClick={() => navigate(`/food-trucks/${truck.id}`)}
                  >
                    View Details
                  </Button>
                  <Button 
                    variant="outlined" 
                    size="small" 
                    onClick={() => navigate(`/food-trucks/${truck.id}/add-menu`)}
                  >
                    Add Menu Item
                  </Button>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default FoodTrucks;
