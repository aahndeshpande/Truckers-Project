package com.aditya.trucker.controller;

import com.aditya.trucker.model.OperatingHours;
import com.aditya.trucker.service.OperatingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operatinghours")
public class OperatingHoursController {

    @Autowired
    private OperatingHoursService operatingHoursService;

    @GetMapping
    public ResponseEntity<List<OperatingHours>> getAllOperatingHours() {
        return ResponseEntity.ok(operatingHoursService.getAllOperatingHours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatingHours> getOperatingHoursById(@PathVariable Long id) {
        return operatingHoursService.getOperatingHoursById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
