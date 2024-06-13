package br.edu.univas.si7.material.water.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.univas.si7.material.water.dtos.MaterialDTO;
import br.edu.univas.si7.material.water.entities.MaterialEntity;
import br.edu.univas.si7.material.water.service.MaterialService;
import br.edu.univas.si7.material.water.util.MaterialEntityConverter;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService service;

    @GetMapping("/{code}")
    public ResponseEntity<?> getProductById(@PathVariable long code) {
        try {
            MaterialEntity entity = service.findById(code);
            return ResponseEntity.ok(MaterialEntityConverter.toDTO(entity));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody MaterialDTO material) {
        try {
            service.createProduct(material);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> updateProduct(@RequestBody MaterialDTO dto, @PathVariable long code) {
        try {
            service.updateMaterial(dto, code);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
