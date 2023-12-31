package com.sampleproject.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id; 
	
	private String firstname;
	
	private String lastname;
	
	private String street;
	
	private String city;
	
}
