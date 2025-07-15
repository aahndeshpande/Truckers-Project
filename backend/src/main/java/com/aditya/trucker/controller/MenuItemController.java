package com.aditya.trucker.controller;

import com.aditya.trucker.model.MenuItem;
import com.aditya.trucker.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
    
    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        return ResponseEntity.status(201).body(menuItemService.addMenuItem(menuItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, menuItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/foodtruck/{foodTruckId}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByFoodTruckId(@PathVariable Long foodTruckId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByFoodTruckId(foodTruckId));
    }

    @GetMapping("/foodtruck/name/{foodTruckName}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByFoodTruckName(@PathVariable String foodTruckName) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByFoodTruckName(foodTruckName));
    }
}
