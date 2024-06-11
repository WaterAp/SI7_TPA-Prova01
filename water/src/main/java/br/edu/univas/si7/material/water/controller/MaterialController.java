package br.edu.univas.si7.material.water.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univas.si7.material.water.dtos.*;
import br.edu.univas.si7.material.water.entities.*;
import br.edu.univas.si7.material.water.service.*;
import br.edu.univas.si7.material.water.util.*;



@RestController
@RequestMapping("/api/materials")
public class MaterialController {
	
	@Autowired
	private MaterialService service;
	
	@GetMapping("/{code}")
	public MaterialDTO getProductById(@PathVariable Integer code) {
		MaterialEntity entity = service.findById(code);
		return MaterialEntityConverter.toDTO(entity);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody MaterialDTO material) {
		service.createProduct(material);
	}
	
	@PutMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduct(@RequestBody MaterialDTO dto, @PathVariable Integer code) {
		service.updateMaterial(dto, code);
	}

}
