package br.edu.univas.si7.material.water.util;

import org.springframework.stereotype.Component;

import br.edu.univas.si7.material.water.dtos.MaterialDTO;
import br.edu.univas.si7.material.water.entities.MaterialEntity;


@Component
public class MaterialEntityConverter {

	public static MaterialDTO toDTO(MaterialEntity material) {
		return new MaterialDTO(
				material.getCode(), material.getName(), 
				material.getPrice(), material.getProvider(), 
				material.getLastBuyDate(), material.isActive());
	}
	
	public MaterialEntity toEntity(MaterialDTO mat) {
		System.out.println("toEntity: " + mat);
		return new MaterialEntity(mat.getCode(), mat.getName(),
				mat.getPrice(), mat.getProvider(), mat.getLastBuyDate(), mat.isActive());
	}
	
}