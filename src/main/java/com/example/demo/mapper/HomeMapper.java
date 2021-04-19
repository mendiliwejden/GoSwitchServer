package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.example.demo.models.FileDB;
import com.example.demo.models.Home;
import com.example.demo.models.dto.HomeDTO;

public class HomeMapper {
	public HomeDTO mapToDTO(Home entity) {

		HomeDTO result = null;
		if (entity != null) {
			result = new HomeDTO();
			result.setId(entity.getId());
			result.setUserId(entity.getUserId());
			result.setEtages(entity.getEtages());
			result.setLits(entity.getLits());
			result.setBalcons(entity.getBalcons());
			result.setTerrasses(entity.getTerrasses());
			result.setBains(entity.getBains());
			result.setChambres(entity.getChambres());
			result.setLogement(entity.getLogement());
			result.setRegion(entity.getRegion());
			result.setTitle(entity.getTitle());
			result.setDebut(entity.getDebut());
			result.setEnd(entity.getEnd());
			result.setPublished(entity.isPublished());
			result.setWifi(entity.isWifi());
			result.setTv(entity.isTV());
			result.setMicroondes(entity.isMicroondes());
			result.setRefrigrrateur(entity.isRefrigrrateur());
			result.setEscalier(entity.isEscalier());
			result.setJacuzzi(entity.isJacuzzi());
			result.setMagazin(entity.isMagazin());
			result.setPiscine(entity.isPiscine());
			result.setGazon(entity.isGazon());
			result.setAnimaux(entity.isAnimaux());
			result.setRadio(entity.isRadio());
			result.setEquipepourenfants(entity.isEquipepourenfants());
			result.setBicyclette(entity.isBicyclette());
			result.setParking(entity.isParking());
			result.setFumeurs(entity.isFumeurs());
			result.setPhotos(extractPhotos(entity));

		}
		return result;
	}

	private List<String> extractPhotos(Home entity) {
		List<String> images = new ArrayList<>();
		for (FileDB dbFile : entity.getPhotos()) {
			String encodedString = Base64.getEncoder().encodeToString(dbFile.getData());
			images.add("data:" + dbFile.getType()+ ";base64," + encodedString);
		}
		return images;

	}
}
