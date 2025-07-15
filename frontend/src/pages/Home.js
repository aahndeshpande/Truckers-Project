import React, { useState } from 'react';
import { Container, Grid, Typography, Button, TextField, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { GoogleMap, LoadScript } from '@react-google-maps/api';

const containerStyle = {
  width: '100%',
  height: '400px',
};

const Home = () => {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = () => {
    navigate(`/search?query=${encodeURIComponent(searchTerm)}`);
  };

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Grid container spacing={4}>
        <Grid item xs={12}>
          <Typography variant="h3" component="h1" gutterBottom>
            Welcome to Food Truck Finder
          </Typography>
          <Typography variant="h5" color="text.secondary" paragraph>
            Find your favorite food trucks near you!
          </Typography>
        </Grid>

        <Grid item xs={12}>
          <Box sx={{ display: 'flex', gap: 2, mb: 4 }}>
            <TextField
              fullWidth
              variant="outlined"
              placeholder="Search for food trucks..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              sx={{ flexGrow: 1 }}
            />
            <Button
              variant="contained"
              onClick={handleSearch}
              sx={{ minWidth: 120 }}
            >
              Search
            </Button>
          </Box>
        </Grid>

        <Grid item xs={12}>
          <LoadScript googleMapsApiKey="YOUR_GOOGLE_MAPS_API_KEY">
            <GoogleMap
              mapContainerStyle={containerStyle}
              zoom={12}
              center={{ lat: 37.7749, lng: -122.4194 }}
            >
              {/* Food truck markers will be added here */}
            </GoogleMap>
          </LoadScript>
        </Grid>

        <Grid item xs={12}>
          <Typography variant="h4" gutterBottom>
            Featured Food Trucks
          </Typography>
          <Grid container spacing={3}>
            {/* Featured food trucks will be displayed here */}
          </Grid>
        </Grid>
      </Grid>
    </Container>
  );
};

export default Home;
