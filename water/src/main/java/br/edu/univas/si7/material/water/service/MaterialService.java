package br.edu.univas.si7.material.water.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.univas.si7.material.water.dtos.MaterialDTO;
import br.edu.univas.si7.material.water.entities.*;
import br.edu.univas.si7.material.water.repositories.*;
import br.edu.univas.si7.material.water.util.*;


@Service
public class MaterialService {
	private MaterialRepository repo;
	
	@Autowired
	private MaterialEntityConverter converter;
	
	@Autowired
	public MaterialService(MaterialRepository repo) {
		this.repo = repo;
	}
	
	public MaterialEntity findById(long code) {
		Optional<MaterialEntity> obj = repo.findById(Long.valueOf(code));
		MaterialEntity entity = obj.orElseThrow(() -> new RuntimeException("Object not found: " + code));
		return entity;
	}
	
	public void createProduct(MaterialDTO material) {
	    validateMaterial(material);
	    MaterialEntity materialEntity = converter.toEntity(material);
	    System.out.println("Trying to create material with code: " + materialEntity.getCode());
	    
	    if (repo.findById(materialEntity.getCode()).isPresent()) {
	        throw new RuntimeException("Material with code " + materialEntity.getCode() + " already exists.");
	    }
	    repo.save(materialEntity);
	}

	
	private void validateMaterial(MaterialDTO material) {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null.");
        }
        if (material.getName() == null || material.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Material name cannot be empty.");
        }
        if (material.getPrice() <= 0) {
            throw new IllegalArgumentException("Material price must be greater than 0.");
        }
        if (material.getProvider() == null || material.getProvider().trim().isEmpty()) {
            throw new IllegalArgumentException("Material provider cannot be empty.");
        }
        if (material.getLastBuyDate() == null || material.getLastBuyDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Material last buy date cannot be empty.");
        }
    }
	
	public void updateMaterial(MaterialDTO material, long code) {
		if (material == null) {
	        throw new RuntimeException("Material data is null.");
	    }
	    if (material.getCode() != code) {  
	        throw new RuntimeException("Invalid product code.");
	    }
		MaterialEntity existingObj = findById(code);
		updateData(existingObj);
		repo.save(existingObj);
	}
	
	private void updateData(MaterialEntity existingObj) {
		if(existingObj.isActive()) {
			existingObj.setActive(false);
		} else {
			existingObj.setActive(true);
		}
	}

}
