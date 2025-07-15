import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchFoodTrucks = createAsyncThunk(
  'foodTrucks/fetchFoodTrucks',
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get('http://localhost:8080/api/food-trucks');
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch food trucks');
    }
  }
);

export const addFoodTruck = createAsyncThunk(
  'foodTrucks/addFoodTruck',
  async (foodTruckData, { rejectWithValue }) => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.post(
        'http://localhost:8080/api/food-trucks',
        foodTruckData,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response?.data?.message || 'Failed to add food truck');
    }
  }
);

export const fetchFoodTruck = createAsyncThunk(
  'foodTrucks/fetchFoodTruck',
  async (id, { rejectWithValue }) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/food-trucks/${id}`);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch food truck');
    }
  }
);

const initialState = {
  foodTrucks: [],
  selectedFoodTruck: null,
  loading: false,
  error: null,
};

const foodTruckSlice = createSlice({
  name: 'foodTrucks',
  initialState,
  reducers: {
    clearSelectedFoodTruck: (state) => {
      state.selectedFoodTruck = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchFoodTrucks.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchFoodTrucks.fulfilled, (state, action) => {
        state.loading = false;
        state.foodTrucks = action.payload;
      })
      .addCase(fetchFoodTrucks.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(addFoodTruck.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(addFoodTruck.fulfilled, (state, action) => {
        state.loading = false;
        state.foodTrucks.push(action.payload);
      })
      .addCase(addFoodTruck.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(fetchFoodTruck.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchFoodTruck.fulfilled, (state, action) => {
        state.loading = false;
        state.selectedFoodTruck = action.payload;
      })
      .addCase(fetchFoodTruck.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { clearSelectedFoodTruck } = foodTruckSlice.actions;
export default foodTruckSlice.reducer;
