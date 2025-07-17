package com.aditya.trucker.service;

import com.aditya.trucker.entity.FoodTruck;
import com.aditya.trucker.exception.ResourceNotFoundException;
import com.aditya.trucker.repository.FoodTruckRepository;
import com.aditya.trucker.service.impl.FoodTruckServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodTruckServiceTest {

    @Mock
    private FoodTruckRepository foodTruckRepository;

    @InjectMocks
    private FoodTruckServiceImpl foodTruckService;

    private FoodTruck foodTruck;

    @BeforeEach
    void setUp() {
        foodTruck = new FoodTruck();
        foodTruck.setId(1L);
        foodTruck.setName("Test Food Truck");
        foodTruck.setDescription("A test food truck");
    }

    @Test
    void getAllFoodTrucks_ShouldReturnAllFoodTrucks() {
        // Arrange
        when(foodTruckRepository.findAll()).thenReturn(Arrays.asList(foodTruck));

        // Act
        List<FoodTruck> result = foodTruckService.getAllFoodTrucks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Food Truck", result.get(0).getName());
        verify(foodTruckRepository, times(1)).findAll();
    }

    @Test
    void getFoodTruckById_WithValidId_ShouldReturnFoodTruck() {
        // Arrange
        when(foodTruckRepository.findById(1L)).thenReturn(Optional.of(foodTruck));

        // Act
        FoodTruck result = foodTruckService.getFoodTruckById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Test Food Truck", result.getName());
        verify(foodTruckRepository, times(1)).findById(1L);
    }

    @Test
    void getFoodTruckById_WithInvalidId_ShouldThrowException() {
        // Arrange
        when(foodTruckRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            foodTruckService.getFoodTruckById(999L);
        });
        
        verify(foodTruckRepository, times(1)).findById(999L);
    }

    @Test
    void createFoodTruck_WithValidData_ShouldReturnSavedFoodTruck() {
        // Arrange
        when(foodTruckRepository.save(any(FoodTruck.class))).thenReturn(foodTruck);

        // Act
        FoodTruck result = foodTruckService.createFoodTruck(foodTruck);

        // Assert
        assertNotNull(result);
        assertEquals("Test Food Truck", result.getName());
        verify(foodTruckRepository, times(1)).save(any(FoodTruck.class));
    }

    @Test
    void updateFoodTruck_WithValidData_ShouldReturnUpdatedFoodTruck() {
        // Arrange
        FoodTruck updatedFoodTruck = new FoodTruck();
        updatedFoodTruck.setName("Updated Food Truck");
        updatedFoodTruck.setDescription("Updated description");

        when(foodTruckRepository.findById(1L)).thenReturn(Optional.of(foodTruck));
        when(foodTruckRepository.save(any(FoodTruck.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        FoodTruck result = foodTruckService.updateFoodTruck(1L, updatedFoodTruck);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Food Truck", result.getName());
        assertEquals("Updated description", result.getDescription());
        verify(foodTruckRepository, times(1)).findById(1L);
        verify(foodTruckRepository, times(1)).save(any(FoodTruck.class));
    }

    @Test
    void deleteFoodTruck_WithValidId_ShouldDeleteFoodTruck() {
        // Arrange
        when(foodTruckRepository.existsById(1L)).thenReturn(true);
        doNothing().when(foodTruckRepository).deleteById(1L);

        // Act
        foodTruckService.deleteFoodTruck(1L);

        // Assert
        verify(foodTruckRepository, times(1)).existsById(1L);
        verify(foodTruckRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteFoodTruck_WithInvalidId_ShouldThrowException() {
        // Arrange
        when(foodTruckRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            foodTruckService.deleteFoodTruck(999L);
        });
        
        verify(foodTruckRepository, times(1)).existsById(999L);
        verify(foodTruckRepository, never()).deleteById(anyLong());
    }
}
