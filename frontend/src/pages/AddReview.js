import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Paper, TextField, Button, Typography, Box, Rating } from '@mui/material';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import axios from 'axios';

const validationSchema = yup.object({
  rating: yup.number().required('Rating is required').min(1).max(5),
  comment: yup.string().required('Comment is required'),
});

const AddReview = () => {
  const { id } = useParams();
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [rating, setRating] = useState(0);

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

      await axios.post(`http://localhost:8080/api/food-trucks/${id}/reviews`, {
        ...data,
        rating: rating,
      }, {
        headers: { Authorization: `Bearer ${token}` }
      });
      setSuccess('Review added successfully!');
      setError('');
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to add review');
      setSuccess('');
    }
  };

  return (
    <Container maxWidth="md" sx={{ mt: 4, mb: 4 }}>
      <Paper elevation={3} sx={{ p: 4 }}>
        <Typography variant="h4" component="h1" gutterBottom>
          Add Review
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
          <Rating
            value={rating}
            onChange={(event, newValue) => setRating(newValue)}
            size="large"
            sx={{ mb: 3 }}
          />

          <TextField
            fullWidth
            label="Comment"
            margin="normal"
            multiline
            rows={4}
            {...register('comment')}
            error={!!errors.comment}
            helperText={errors.comment?.message}
          />

          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{ mt: 3, mb: 2 }}
          >
            Submit Review
          </Button>
        </Box>
      </Paper>
    </Container>
  );
};

export default AddReview;
