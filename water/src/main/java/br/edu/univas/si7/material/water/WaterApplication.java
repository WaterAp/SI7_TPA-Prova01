package br.edu.univas.si7.material.water;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.si7.material.water.entities.MaterialEntity;
import br.edu.univas.si7.material.water.repositories.MaterialRepository;


@SpringBootApplication
public class WaterApplication implements CommandLineRunner {
	
	@Autowired
	private MaterialRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(WaterApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		MaterialEntity m1 = new MaterialEntity(1, "ferro", 7.30f, "proverdor 1", "16/04/2024",  true);
		repo.save(m1);
		
		
		List<MaterialEntity> materiais = repo.findAll();
		System.out.println(materiais);
	}

}
