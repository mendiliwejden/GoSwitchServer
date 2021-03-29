package com.example.demo.models.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HomeDTO {

	private Long id;

	private Long userId;

	private int etages;

	private int lits;
	private int balcons;
	private int terrasses;
	private int bains;
	private int chambres;
	private String logement;
	private String region;
	private String title;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date debut;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date end;

	private List<String> photos;
	private boolean published;

	private boolean wifi;
	private boolean tv;
	private boolean microondes;
	private boolean refrigrrateur;
	private boolean escalier;
	private boolean jacuzzi;
	private boolean magazin;
	private boolean piscine;
	private boolean gazon;
	private boolean animaux;
	private boolean radio;
	private boolean equipepourenfants;
	private boolean bicyclette;
	private boolean parking;
	private boolean fumeurs;
	private MultipartFile file;
}
