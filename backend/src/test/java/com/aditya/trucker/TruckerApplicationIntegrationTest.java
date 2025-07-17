package com.aditya.trucker;

import com.aditya.trucker.entity.FoodTruck;
import com.aditya.trucker.repository.FoodTruckRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TruckerApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    private FoodTruck testFoodTruck;

    @BeforeEach
    void setup() {
        // Setup test data
        testFoodTruck = new FoodTruck();
        testFoodTruck.setName("Integration Test Truck");
        testFoodTruck.setDescription("This is a test food truck");
        testFoodTruck = foodTruckRepository.save(testFoodTruck);
    }

    @AfterEach
    void tearDown() {
        // Clean up test data
        foodTruckRepository.deleteAll();
    }

    @Test
    void whenGetAllFoodTrucks_thenReturnFoodTrucksList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/food-trucks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].name", is(testFoodTruck.getName())));
    }

    @Test
    void whenGetFoodTruckById_thenReturnFoodTruck() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/food-trucks/" + testFoodTruck.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(testFoodTruck.getName())));
    }

    @Test
    void whenCreateFoodTruck_thenReturnCreatedFoodTruck() throws Exception {
        // Given
        String foodTruckJson = "{\"name\":\"New Food Truck\",\"description\":\"A new food truck\"}";

        // When & Then
        mockMvc.perform(post("/api/food-trucks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(foodTruckJson))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.name", is("New Food Truck")));
    }

    @Test
    void whenUpdateFoodTruck_thenReturnUpdatedFoodTruck() throws Exception {
        // Given
        String updatedFoodTruckJson = "{\"name\":\"Updated Food Truck\",\"description\":\"Updated description\"}";

        // When & Then
        mockMvc.perform(put("/api/food-trucks/" + testFoodTruck.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedFoodTruckJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Food Truck")));
    }

    @Test
    void whenDeleteFoodTruck_thenReturnNoContent() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/food-trucks/" + testFoodTruck.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify the food truck was deleted
        mockMvc.perform(get("/api/food-trucks/" + testFoodTruck.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenSearchFoodTrucks_thenReturnMatchingFoodTrucks() throws Exception {
        // Given - Create another test food truck
        FoodTruck anotherTruck = new FoodTruck();
        anotherTruck.setName("Pizza Truck");
        anotherTruck.setDescription("Serving delicious pizza");
        foodTruckRepository.save(anotherTruck);

        // When & Then - Search for "pizza"
        mockMvc.perform(get("/api/food-trucks/search")
                .param("query", "pizza")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Pizza Truck")));
    }

    @Test
    void whenCreateFoodTruckWithInvalidData_thenReturnBadRequest() throws Exception {
        // Given - Invalid food truck (missing required name)
        String invalidFoodTruckJson = "{\"description\":\"Missing name\"}";

        // When & Then
        mockMvc.perform(post("/api/food-trucks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidFoodTruckJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name", is("Name is required")));
    }
}
