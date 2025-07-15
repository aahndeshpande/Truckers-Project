import React from 'react';
import { CircularProgress, Box } from '@mui/material';
import { styled } from '@mui/material/styles';

const StyledBox = styled(Box)(({ theme }) => ({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  minHeight: '200px',
  backgroundColor: theme.palette.background.paper,
  borderRadius: theme.shape.borderRadius,
  padding: theme.spacing(2),
}));

const LoadingSpinner = ({ message = 'Loading...' }) => (
  <StyledBox>
    <CircularProgress size={40} sx={{ mr: 2 }} />
    <Typography variant="body1" color="textSecondary">
      {message}
    </Typography>
  </StyledBox>
);

export default LoadingSpinner;
