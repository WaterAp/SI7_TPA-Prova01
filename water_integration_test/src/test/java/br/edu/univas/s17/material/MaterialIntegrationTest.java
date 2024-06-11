package br.edu.univas.s17.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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
	private final String materialURL = "http://localhost:8080/api/order";
	

	@Test
	public void testGetMaterialByCode() {
		int MaterialCode = 1;
		try {
			Response resp = RestAssured.get(materialURL + "/" + MaterialCode);
			assertEquals(HttpStatus.OK.value(), resp.getStatusCode());
			
			String jsonBody = resp.getBody().asString();
			MaterialDTO material = mapper.readValue(jsonBody, MaterialDTO.class);
			
			assertNotNull(material);
			assertEquals(1, material.getCode());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			fail("Fail to get material with code: " + MaterialCode, e);
		}
	}

	@Test
	public void testPostMaterial() {
		MaterialDTO material = new MaterialDTO(1, "ferro", 7.30f, "proverdor 1", "16/04/2024",  true);
		Response resp = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(material)
				.post(materialURL);
		assertEquals(HttpStatus.CREATED.value(), resp.getStatusCode());
	}

}
