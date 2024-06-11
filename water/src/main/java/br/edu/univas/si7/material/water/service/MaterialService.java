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
	
	public MaterialEntity findById(Integer code) {
		Optional<MaterialEntity> obj = repo.findById(code);
		MaterialEntity entity = obj.orElseThrow(() -> new RuntimeException("Object not found: " + code));
		return entity;
	}
	
	public void createProduct(MaterialDTO material) {
		repo.save(converter.toEntity(material));
	}
	
	public void updateMaterial(MaterialDTO material, Integer code) {
		if (code == null || material == null || !code.equals(material.getCode())) {
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
