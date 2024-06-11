package br.edu.univas.si7.material.water.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MaterialDTO {
	private long code;
	private String name;
	private float price;
	private String provider;
	private String lastBuyDate;
	
	//@JsonIgnore
	private boolean active;
}
