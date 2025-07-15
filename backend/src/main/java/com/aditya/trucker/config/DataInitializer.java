package com.aditya.trucker.config;

import com.aditya.trucker.model.*;
import com.aditya.trucker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final FoodTruckRepository foodTruckRepository;
    private final MenuItemRepository menuItemRepository;
    private final CommunityRepository communityRepository;
    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            // Create sample users
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(List.of("ROLE_ADMIN"));
            admin.setEmail("admin@trucker.com");
            userRepository.save(admin);

            User owner = new User();
            owner.setUsername("owner1");
            owner.setPassword(passwordEncoder.encode("owner123"));
            owner.setRoles(List.of("ROLE_OWNER"));
            owner.setEmail("owner1@trucker.com");
            userRepository.save(owner);

            User user1 = new User();
            user1.setUsername("user1");
            user1.setPassword(passwordEncoder.encode("user123"));
            user1.setRoles(List.of("ROLE_USER"));
            user1.setEmail("user1@trucker.com");
            userRepository.save(user1);

            // Create sample food truck
            FoodTruck foodTruck = new FoodTruck();
            foodTruck.setName("Burger Bonanza");
            foodTruck.setDescription("Best burgers in town!");
            foodTruck.setLocation("Downtown");
            foodTruck.setOwner(owner);
            foodTruckRepository.save(foodTruck);

            // Create menu items
            MenuItem item1 = new MenuItem();
            item1.setName("Classic Burger");
            item1.setDescription("Juicy beef patty with lettuce, tomato, and special sauce");
            item1.setPrice(8.99);
            item1.setFoodTruck(foodTruck);
            menuItemRepository.save(item1);

            MenuItem item2 = new MenuItem();
            item2.setName("Cheeseburger");
            item2.setDescription("Classic burger with extra cheese");
            item2.setPrice(9.99);
            item2.setFoodTruck(foodTruck);
            menuItemRepository.save(item2);

            // Create community
            Community community = new Community();
            community.setName("Foodie Community");
            community.setDescription("Community for food lovers");
            community.setOwner(owner);
            communityRepository.save(community);

            // Create event
            Event event = new Event();
            event.setTitle("Food Truck Festival");
            event.setDescription("Join us for a weekend of delicious food");
            event.setDateTime("2025-07-20T12:00:00");
            event.setLocation("Central Park");
            event.setCommunity(community);
            event.setFoodTruck(foodTruck);
            eventRepository.save(event);
        };
    }
}
