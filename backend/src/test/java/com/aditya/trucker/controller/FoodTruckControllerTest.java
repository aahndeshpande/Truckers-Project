package com.aditya.trucker.controller;

import com.aditya.trucker.dto.FoodTruckDTO;
import com.aditya.trucker.entity.FoodTruck;
import com.aditya.trucker.service.FoodTruckService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class FoodTruckControllerTest {

    @Mock
    private FoodTruckService foodTruckService;

    @InjectMocks
    private FoodTruckController foodTruckController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private FoodTruck foodTruck;
    private FoodTruckDTO foodTruckDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(foodTruckController).build();
        objectMapper = new ObjectMapper();

        foodTruck = new FoodTruck();
        foodTruck.setId(1L);
        foodTruck.setName("Test Truck");
        foodTruck.setDescription("Test Description");

        foodTruckDTO = new FoodTruckDTO();
        foodTruckDTO.setId(1L);
        foodTruckDTO.setName("Test Truck");
        foodTruckDTO.setDescription("Test Description");
    }

    @Test
    void getAllFoodTrucks_ShouldReturnListOfFoodTrucks() throws Exception {
        when(foodTruckService.getAllFoodTrucks()).thenReturn(Arrays.asList(foodTruck));

        mockMvc.perform(get("/api/food-trucks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Truck"));
    }

    @Test
    void getFoodTruckById_WithValidId_ShouldReturnFoodTruck() throws Exception {
        when(foodTruckService.getFoodTruckById(1L)).thenReturn(foodTruck);

        mockMvc.perform(get("/api/food-trucks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Truck"));
    }

    @Test
    void createFoodTruck_WithValidData_ShouldReturnCreated() throws Exception {
        when(foodTruckService.createFoodTruck(any(FoodTruck.class))).thenReturn(foodTruck);

        mockMvc.perform(post("/api/food-trucks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(foodTruckDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/food-trucks/1"));
    }

    @Test
    void updateFoodTruck_WithValidData_ShouldReturnUpdatedFoodTruck() throws Exception {
        FoodTruck updatedFoodTruck = new FoodTruck();
        updatedFoodTruck.setId(1L);
        updatedFoodTruck.setName("Updated Truck");
        updatedFoodTruck.setDescription("Updated Description");

        when(foodTruckService.updateFoodTruck(anyLong(), any(FoodTruck.class))).thenReturn(updatedFoodTruck);

        FoodTruckDTO updatedDTO = new FoodTruckDTO();
        updatedDTO.setName("Updated Truck");
        updatedDTO.setDescription("Updated Description");

        mockMvc.perform(put("/api/food-trucks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Truck"));
    }

    @Test
    void deleteFoodTruck_WithValidId_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/food-trucks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void searchFoodTrucks_WithQuery_ShouldReturnMatchingFoodTrucks() throws Exception {
        when(foodTruckService.searchFoodTrucks("taco")).thenReturn(Arrays.asList(foodTruck));

        mockMvc.perform(get("/api/food-trucks/search")
                .param("query", "taco")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Truck"));
    }

    @Test
    void createFoodTruck_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        FoodTruckDTO invalidDTO = new FoodTruckDTO();
        invalidDTO.setName(""); // Name is required

        mockMvc.perform(post("/api/food-trucks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }
}
