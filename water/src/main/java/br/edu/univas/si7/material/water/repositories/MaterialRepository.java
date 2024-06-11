package br.edu.univas.si7.material.water.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.univas.si7.material.water.entities.MaterialEntity;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Integer> {

	public List<MaterialEntity> findByActive(Boolean active);

}