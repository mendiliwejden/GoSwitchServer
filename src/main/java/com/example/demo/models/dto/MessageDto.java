package com.example.demo.models.dto;

import lombok.Data;

@Data
public class MessageDto {

	private String nom;
	private String phoneNumber;
	private String email;
	private String sujet;
	private String message;
}
