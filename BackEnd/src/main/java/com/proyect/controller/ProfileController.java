package com.proyect.controller;

import com.proyect.dto.ProfileDTO;
import com.proyect.service.ProfileServive;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("profiles")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Profile", description = "Controller for profiles - permisos.")
public class ProfileController {

    private final ProfileServive profileServive;

    @GetMapping
    public ResponseEntity<Set<ProfileDTO>> findAll() {
        return ResponseEntity.ok(profileServive.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(profileServive.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> saveProfile(@RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileServive.saveProfile(profileDTO));
    }

    @PutMapping
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileServive.updateProfile(profileDTO));
    }
}
