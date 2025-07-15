import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Grid, Card, CardContent, Typography, Box, Button, Rating } from '@mui/material';
import { LoadScript, GoogleMap, Marker } from '@react-google-maps/api';
import axios from 'axios';

const containerStyle = {
  width: '100%',
  height: '400px',
};

const FoodTruckDetails = () => {
  const { id } = useParams();
  const [foodTruck, setFoodTruck] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchFoodTruck = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/food-trucks/${id}`);
        setFoodTruck(response.data);
      } catch (err) {
        setError(err.response?.data?.message || 'Failed to fetch food truck details');
      } finally {
        setLoading(false);
      }
    };

    fetchFoodTruck();
  }, [id]);

  if (loading) {
    return (
      <Container sx={{ mt: 4, mb: 4 }}>
        <Typography>Loading food truck details...</Typography>
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
      <Box sx={{ mb: 4 }}>
        <Typography variant="h4">{foodTruck.name}</Typography>
        <Typography variant="h6" color="text.secondary">
          {foodTruck.cuisineType}
        </Typography>
        <Rating value={foodTruck.averageRating} readOnly />
      </Box>

      <Grid container spacing={3}>
        <Grid item xs={12} md={8}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Description
              </Typography>
              <Typography>{foodTruck.description}</Typography>

              <Typography variant="h6" gutterBottom sx={{ mt: 3 }}>
                Menu Items
              </Typography>
              <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                {foodTruck.menuItems.map((item) => (
                  <Box key={item.id} sx={{ display: 'flex', justifyContent: 'space-between', p: 1 }}>
                    <Typography>{item.name}</Typography>
                    <Typography>${item.price}</Typography>
                  </Box>
                ))}
              </Box>

              <Typography variant="h6" gutterBottom sx={{ mt: 3 }}>
                Operating Hours
              </Typography>
              <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1 }}>
                {foodTruck.operatingHours.map((hour) => (
                  <Typography key={hour.dayOfWeek}>
                    {hour.dayOfWeek}: {hour.openTime} - {hour.closeTime}
                  </Typography>
                ))}
              </Box>
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Location
              </Typography>
              <LoadScript googleMapsApiKey="YOUR_GOOGLE_MAPS_API_KEY">
                <GoogleMap
                  mapContainerStyle={containerStyle}
                  zoom={15}
                  center={{ lat: foodTruck.latitude, lng: foodTruck.longitude }}
                >
                  <Marker position={{ lat: foodTruck.latitude, lng: foodTruck.longitude }} />
                </GoogleMap>
              </LoadScript>
            </CardContent>
          </Card>

          <Button 
            variant="contained" 
            fullWidth 
            sx={{ mt: 2 }}
            onClick={() => window.location.href = `/food-trucks/${id}/add-review`}
          >
            Add Review
          </Button>
        </Grid>
      </Grid>
    </Container>
  );
};

export default FoodTruckDetails;
