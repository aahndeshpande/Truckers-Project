import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Container, Grid, TextField, Button, Typography, Box, Card, CardContent } from '@mui/material';
import { LoadScript, GoogleMap, Marker } from '@react-google-maps/api';

const containerStyle = {
  width: '100%',
  height: '400px',
};

const Search = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');
  const [cuisineType, setCuisineType] = useState('');
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const query = searchParams.get('query');
    setSearchTerm(query || '');

    const searchFoodTrucks = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/food-trucks/search?query=${encodeURIComponent(searchTerm)}&cuisineType=${cuisineType}`);
        const data = await response.json();
        setResults(data);
      } catch (err) {
        setError('Failed to fetch search results');
      } finally {
        setLoading(false);
      }
    };

    searchFoodTrucks();
  }, [location.search, searchTerm, cuisineType]);

  const handleSearch = () => {
    navigate(`/search?query=${encodeURIComponent(searchTerm)}&cuisineType=${cuisineType}`);
  };

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Box sx={{ mb: 4 }}>
        <Typography variant="h4">Search Food Trucks</Typography>
      </Box>

      <Box sx={{ display: 'flex', gap: 2, mb: 4 }}>
        <TextField
          fullWidth
          label="Search"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          sx={{ flexGrow: 1 }}
        />
        <TextField
          select
          label="Cuisine Type"
          value={cuisineType}
          onChange={(e) => setCuisineType(e.target.value)}
          sx={{ minWidth: 200 }}
        >
          <option value="">All</option>
          <option value="American">American</option>
          <option value="Mexican">Mexican</option>
          <option value="Italian">Italian</option>
          <option value="Asian">Asian</option>
          <option value="Other">Other</option>
        </TextField>
        <Button variant="contained" onClick={handleSearch}>
          Search
        </Button>
      </Box>

      {loading && (
        <Typography>Loading search results...</Typography>
      )}

      {error && (
        <Typography color="error">{error}</Typography>
      )}

      <Grid container spacing={3}>
        {results.map((truck) => (
          <Grid item xs={12} sm={6} md={4} key={truck.id}>
            <Card>
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
                <Box sx={{ mt: 2 }}>
                  <Typography variant="subtitle2">
                    Location: {truck.address}
                  </Typography>
                  <LoadScript googleMapsApiKey="YOUR_GOOGLE_MAPS_API_KEY">
                    <GoogleMap
                      mapContainerStyle={{ width: '100%', height: '200px' }}
                      zoom={15}
                      center={{ lat: truck.latitude, lng: truck.longitude }}
                    >
                      <Marker position={{ lat: truck.latitude, lng: truck.longitude }} />
                    </GoogleMap>
                  </LoadScript>
                </Box>
                <Button 
                  variant="contained" 
                  fullWidth 
                  onClick={() => navigate(`/food-trucks/${truck.id}`)}
                  sx={{ mt: 2 }}
                >
                  View Details
                </Button>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default Search;
