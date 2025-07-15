import React, { useState, useEffect } from 'react';
import { Container, Grid, Card, CardContent, Typography, Box, Button, List, ListItem, ListItemText } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Profile = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [foodTrucks, setFoodTrucks] = useState([]);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) {
          navigate('/login');
          return;
        }

        const response = await axios.get('http://localhost:8080/api/users/me', {
          headers: { Authorization: `Bearer ${token}` }
        });
        setUser(response.data);

        const trucksResponse = await axios.get('http://localhost:8080/api/food-trucks/owner', {
          headers: { Authorization: `Bearer ${token}` }
        });
        setFoodTrucks(trucksResponse.data);
      } catch (err) {
        setError(err.response?.data?.message || 'Failed to fetch profile');
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login');
  };

  if (loading) {
    return (
      <Container sx={{ mt: 4, mb: 4 }}>
        <Typography>Loading profile...</Typography>
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
        <Typography variant="h4">Profile</Typography>
        <Button variant="contained" color="error" onClick={handleLogout}>
          Logout
        </Button>
      </Box>

      <Grid container spacing={4}>
        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                User Information
              </Typography>
              <List>
                <ListItem>
                  <ListItemText primary="Name" secondary={user.name} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Email" secondary={user.email} />
                </ListItem>
                <ListItem>
                  <ListItemText primary="Role" secondary={user.role} />
                </ListItem>
              </List>
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={8}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                My Food Trucks
              </Typography>
              {foodTrucks.length === 0 ? (
                <Typography>No food trucks found. Add one now!</Typography>
              ) : (
                <List>
                  {foodTrucks.map((truck) => (
                    <ListItem key={truck.id}>
                      <ListItemText
                        primary={truck.name}
                        secondary={truck.cuisineType}
                      />
                      <Button
                        variant="outlined"
                        size="small"
                        onClick={() => navigate(`/food-trucks/${truck.id}`)}
                      >
                        View
                      </Button>
                    </ListItem>
                  ))}
                </List>
              )}
            </CardContent>
          </Card>

          {user.role === 'OWNER' && (
            <Button
              variant="contained"
              fullWidth
              sx={{ mt: 2 }}
              onClick={() => navigate('/add-food-truck')}
            >
              Add New Food Truck
            </Button>
          )}
        </Grid>
      </Grid>
    </Container>
  );
};

export default Profile;
