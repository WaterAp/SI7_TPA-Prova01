package br.edu.univas.s17.material;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.edu.univas.si7.material.water.dtos.MaterialDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MaterialIntegrationTest {
	
	private ObjectMapper mapper = new ObjectMapper();
	private final String materialURL = "http://localhost:8084/api/materials";
	
	@Test
	public void testPostNewMaterialSuccess() {
		
	    
	    long randomCode = new Random().nextLong(10, 100000); 

	    MaterialDTO newMaterial = new MaterialDTO(randomCode, "Aço", 15.50f, "Fornecedor A", "01/06/2024", true);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(newMaterial)
	        .post(materialURL);

	    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	   	
	}
	
	@Test
	public void testCreateMaterialAlreadyExists() {
	    MaterialDTO duplicateMaterial = new MaterialDTO(1, "Granito", 77.30f, "Provedor 1", "16/04/2024", true);
	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(duplicateMaterial)
	        .post(materialURL);
	    
	    assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCode());
	}


	
	@Test
	public void testCreateMaterialWithInvalidData() {
		
	    MaterialDTO invalidMaterial = new MaterialDTO(0, "", -10.0f, "Provedor 1", "16/04/2024", true);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(invalidMaterial)
	        .post(materialURL);

	    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	    
	}
	
	@Test
	public void testToggleMaterialActiveStatus() {
		
	    long materialCode = 1;

	    MaterialDTO originalMaterial = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .get(materialURL + "/" + materialCode)
	        .as(MaterialDTO.class);

	    boolean originalState = originalMaterial.isActive();

	    originalMaterial.setActive(!originalState);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(originalMaterial)
	        .put(materialURL + "/" + materialCode);
	    assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());

	    MaterialDTO updatedMaterial = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .get(materialURL + "/" + materialCode)
	        .as(MaterialDTO.class);
	    assertNotEquals(originalState, updatedMaterial.isActive());
	    
	}
	
	@Test
	public void testToggleMaterialActiveStatusForNonExistentMaterial() {
		
	    long nonExistentMaterialCode = 9999; 

	    MaterialDTO nonExistentMaterial = new MaterialDTO(nonExistentMaterialCode, "Ouro", 50.0f, "Fornecedor Fictício", "01/01/2025", true);

	    Response response = RestAssured
	        .given()
	        .contentType(ContentType.JSON)
	        .body(nonExistentMaterial)
	        .put(materialURL + "/" + nonExistentMaterialCode);

	    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	    
	}

}

