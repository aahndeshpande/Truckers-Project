package com.aditya.trucker.repository;

import com.aditya.trucker.entity.FoodTruck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FoodTruckRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @Test
    void whenFindById_thenReturnFoodTruck() {
        // given
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setName("Test Truck");
        foodTruck.setDescription("Test Description");
        entityManager.persist(foodTruck);
        entityManager.flush();

        // when
        Optional<FoodTruck> found = foodTruckRepository.findById(foodTruck.getId());

        // then
        assertTrue(found.isPresent());
        assertEquals(foodTruck.getName(), found.get().getName());
    }

    @Test
    void whenFindAll_thenReturnAllFoodTrucks() {
        // given
        FoodTruck truck1 = new FoodTruck();
        truck1.setName("Truck 1");
        truck1.setDescription("Description 1");
        entityManager.persist(truck1);

        FoodTruck truck2 = new FoodTruck();
        truck2.setName("Truck 2");
        truck2.setDescription("Description 2");
        entityManager.persist(truck2);
        
        entityManager.flush();

        // when
        List<FoodTruck> trucks = foodTruckRepository.findAll();

        // then
        assertEquals(2, trucks.size());
        assertTrue(trucks.stream().anyMatch(t -> t.getName().equals("Truck 1")));
        assertTrue(trucks.stream().anyMatch(t -> t.getName().equals("Truck 2")));
    }

    @Test
    void whenSave_thenPersistFoodTruck() {
        // given
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setName("New Truck");
        foodTruck.setDescription("New Description");

        // when
        FoodTruck saved = foodTruckRepository.save(foodTruck);

        // then
        assertNotNull(saved.getId());
        assertEquals("New Truck", saved.getName());
        assertEquals("New Description", saved.getDescription());
    }

    @Test
    void whenUpdate_thenUpdateFoodTruck() {
        // given
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setName("Original Name");
        foodTruck.setDescription("Original Description");
        entityManager.persistAndFlush(foodTruck);

        // when
        foodTruck.setName("Updated Name");
        foodTruck.setDescription("Updated Description");
        FoodTruck updated = foodTruckRepository.save(foodTruck);

        // then
        assertEquals(foodTruck.getId(), updated.getId());
        assertEquals("Updated Name", updated.getName());
        assertEquals("Updated Description", updated.getDescription());
    }

    @Test
    void whenDelete_thenRemoveFoodTruck() {
        // given
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setName("Truck to Delete");
        foodTruck.setDescription("Will be deleted");
        entityManager.persistAndFlush(foodTruck);

        // when
        foodTruckRepository.delete(foodTruck);

        // then
        assertFalse(foodTruckRepository.findById(foodTruck.getId()).isPresent());
    }

    @Test
    void whenFindByNameContainingIgnoreCase_thenReturnMatchingFoodTrucks() {
        // given
        FoodTruck tacoTruck = new FoodTruck();
        tacoTruck.setName("Taco Truck");
        tacoTruck.setDescription("Serves tacos");
        entityManager.persist(tacoTruck);

        FoodTruck burgerTruck = new FoodTruck();
        burgerTruck.setName("Burger Truck");
        burgerTruck.setDescription("Serves burgers");
        entityManager.persist(burgerTruck);

        FoodTruck anotherTacoTruck = new FoodTruck();
        anotherTacoTruck.setName("Another Taco Truck");
        anotherTacoTruck.setDescription("More tacos");
        entityManager.persist(anotherTacoTruck);
        
        entityManager.flush();

        // when
        List<FoodTruck> tacoTrucks = foodTruckRepository.findByNameContainingIgnoreCase("taco");
        List<FoodTruck> burgerTrucks = foodTruckRepository.findByNameContainingIgnoreCase("burger");
        List<FoodTruck> emptyResult = foodTruckRepository.findByNameContainingIgnoreCase("pizza");

        // then
        assertEquals(2, tacoTrucks.size());
        assertEquals(1, burgerTrucks.size());
        assertTrue(emptyResult.isEmpty());
    }
}
