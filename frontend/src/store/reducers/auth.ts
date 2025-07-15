import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import apiClient from '../../api/apiClient';
import { User, AuthResponse, LoginCredentials, RegisterCredentials } from '../../types/api';

export const login = createAsyncThunk(
    'auth/login',
    async (credentials: LoginCredentials) => {
        const response = await apiClient.post<AuthResponse>('/auth/login', credentials);
        return response.data;
    }
);

export const register = createAsyncThunk(
    'auth/register',
    async (credentials: RegisterCredentials) => {
        const response = await apiClient.post<AuthResponse>('/auth/register', credentials);
        return response.data;
    }
);

export const logout = createAsyncThunk(
    'auth/logout',
    async () => {
        await apiClient.post('/auth/logout');
        localStorage.removeItem('token');
        return null;
    }
);

const initialState = {
    user: null as User | null,
    token: localStorage.getItem('token') || null,
    loading: false,
    error: null as string | null,
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(login.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(login.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload.user;
                state.token = action.payload.token;
                localStorage.setItem('token', action.payload.token);
            })
            .addCase(login.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message || 'Login failed';
            })
            .addCase(register.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(register.fulfilled, (state, action) => {
                state.loading = false;
                state.user = action.payload.user;
                state.token = action.payload.token;
                localStorage.setItem('token', action.payload.token);
            })
            .addCase(register.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message || 'Registration failed';
            })
            .addCase(logout.fulfilled, (state) => {
                state.user = null;
                state.token = null;
            });
    },
});

export default authSlice.reducer;

export const selectAuth = (state: RootState) => state.auth;
export const selectIsAuthenticated = (state: RootState) => state.auth.token !== null;
export const selectCurrentUser = (state: RootState) => state.auth.user;
