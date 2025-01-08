package com.proyect.controller;

import com.proyect.dto.RoleDTO;
import com.proyect.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Roles", description = "Controller for Roles.")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Set<RoleDTO>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @GetMapping("searchByName/{name}")
    public ResponseEntity<Boolean> findById(@PathVariable String name) {
        return ResponseEntity.ok(roleService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.saveRol(roleDTO));
    }

    @PutMapping
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.updateRol(roleDTO));
    }
}
