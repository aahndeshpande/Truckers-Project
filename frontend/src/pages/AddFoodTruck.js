import React, { useState } from 'react';
import { Container, Paper, TextField, Button, Typography, Box } from '@mui/material';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import axios from 'axios';

const validationSchema = yup.object({
  name: yup.string().required('Name is required'),
  cuisineType: yup.string().required('Cuisine type is required'),
  description: yup.string().required('Description is required'),
  latitude: yup.number().required('Latitude is required'),
  longitude: yup.number().required('Longitude is required'),
  operatingHours: yup.array().of(
    yup.object({
      dayOfWeek: yup.number().required('Day of week is required'),
      openTime: yup.string().required('Open time is required'),
      closeTime: yup.string().required('Close time is required'),
    })
  ).required('Operating hours are required'),
});

const AddFoodTruck = () => {
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(validationSchema),
  });

  const onSubmit = async (data) => {
    try {
      const token = localStorage.getItem('token');
      if (!token) {
        window.location.href = '/login';
        return;
      }

      await axios.post('http://localhost:8080/api/food-trucks', data, {
        headers: { Authorization: `Bearer ${token}` }
      });
      setSuccess('Food truck added successfully!');
      setError('');
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to add food truck');
      setSuccess('');
    }
  };

  return (
    <Container maxWidth="md" sx={{ mt: 4, mb: 4 }}>
      <Paper elevation={3} sx={{ p: 4 }}>
        <Typography variant="h4" component="h1" gutterBottom>
          Add Food Truck
        </Typography>
        
        {error && (
          <Typography color="error" sx={{ mb: 2 }}>
            {error}
          </Typography>
        )}
        {success && (
          <Typography color="success" sx={{ mb: 2 }}>
            {success}
          </Typography>
        )}

        <Box component="form" onSubmit={handleSubmit(onSubmit)}>
          <TextField
            fullWidth
            label="Name"
            margin="normal"
            {...register('name')}
            error={!!errors.name}
            helperText={errors.name?.message}
          />

          <TextField
            fullWidth
            label="Cuisine Type"
            margin="normal"
            {...register('cuisineType')}
            error={!!errors.cuisineType}
            helperText={errors.cuisineType?.message}
          />

          <TextField
            fullWidth
            label="Description"
            margin="normal"
            multiline
            rows={4}
            {...register('description')}
            error={!!errors.description}
            helperText={errors.description?.message}
          />

          <TextField
            fullWidth
            label="Latitude"
            margin="normal"
            type="number"
            {...register('latitude')}
            error={!!errors.latitude}
            helperText={errors.latitude?.message}
          />

          <TextField
            fullWidth
            label="Longitude"
            margin="normal"
            type="number"
            {...register('longitude')}
            error={!!errors.longitude}
            helperText={errors.longitude?.message}
          />

          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{ mt: 3, mb: 2 }}
          >
            Add Food Truck
          </Button>
        </Box>
      </Paper>
    </Container>
  );
};

export default AddFoodTruck;
