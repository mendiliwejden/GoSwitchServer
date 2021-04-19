package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.HomeMapper;
import com.example.demo.models.Home;
import com.example.demo.models.dto.HomeDTO;
import com.example.demo.repository.HomeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.services.FileStorageService;
import com.example.demo.security.services.HomeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/home")
public class HomeController {

	@Autowired
	HomeRepository homeRepository;

	HomeService homeService;
	HomeMapper mapper = new HomeMapper();

	@Autowired
	UserRepository userRepository;

	@Autowired
	FileStorageService fileStorageService;

	@GetMapping("/allHome")
	public ResponseEntity<List<HomeDTO>> getAllHome() {
		try {
			List<Home> homeList = homeRepository.findAll();
			List<HomeDTO> homeListDto = new ArrayList<>();

			if (!homeList.isEmpty()) {
				homeListDto = homeList.stream().map(home -> mapper.mapToDTO(home)).collect(Collectors.toList());
				return new ResponseEntity<>(homeListDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/HomeListByRegion/{region}")
	public ResponseEntity<List<HomeDTO>> getHomeListByRegion(@PathVariable("region") String region) {
		List<Home> homeList = homeRepository.findByRegionAndPublished(region, true);
		List<HomeDTO> homeListDto = new ArrayList<>();

		if (!homeList.isEmpty()) {
			homeListDto = homeList.stream().map(home -> mapper.mapToDTO(home)).collect(Collectors.toList());
			return new ResponseEntity<>(homeListDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getHome/{id}")
	public ResponseEntity<HomeDTO> getHomeById(@PathVariable("id") long id) {
		Optional<Home> home = homeRepository.findById(id);

		if (home.isPresent()) {
			return new ResponseEntity<>(mapper.mapToDTO(home.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addHome")
	public ResponseEntity<Home> createHome(@RequestParam("home") String homee)
			throws JsonMappingException, JsonProcessingException {
		System.out.println("Ok .............");
		HomeDTO h = new ObjectMapper().readValue(homee, HomeDTO.class);
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentUserName = authentication.getName();
			Long myUser = userRepository.findByUsername(currentUserName).get().getId();

			Home home = new Home();
			home.setEtages(h.getEtages());
			home.setTitle(h.getTitle());
			home.setLits(h.getLits());
			home.setBalcons(h.getBalcons());
			home.setTerrasses(h.getTerrasses());
			home.setUserId(myUser);
			home.setBains(h.getBains());
			home.setChambres(h.getChambres());
			home.setLogement(h.getLogement());
			home.setRegion(h.getRegion());
			home.setDebut(h.getDebut());
			home.setEnd(h.getEnd());
			home.setPublished(false);

			home.setWifi(h.isWifi());
			home.setTV(h.isTv());
			home.setAnimaux(h.isAnimaux());
			home.setBicyclette(h.isBicyclette());
			home.setEquipepourenfants(h.isEquipepourenfants());
			home.setEscalier(h.isEscalier());
			home.setFumeurs(h.isFumeurs());
			home.setGazon(h.isGazon());
			home.setJacuzzi(h.isJacuzzi());
			home.setMagazin(h.isMagazin());
			home.setMicroondes(h.isMicroondes());
			home.setParking(h.isParking());
			home.setPiscine(h.isPiscine());
			home.setRadio(h.isRadio());
			home.setRefrigrrateur(h.isRefrigrrateur());

			homeRepository.save(home);
			return new ResponseEntity<>(home, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/updateHomePublished/{id}/{state}")
	public ResponseEntity<HomeDTO> updateHomePublished(@PathVariable("id") long id,
			@PathVariable("state") boolean state) throws IOException {
		Home result = new Home();
		// home passed
		Home homeData = homeRepository.findById(id).get();

		if (homeData != null) {

			homeData.setPublished(state);
			result = homeRepository.save(homeData);
			return new ResponseEntity<>(mapper.mapToDTO(result), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(mapper.mapToDTO(result), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteHome/{id}")
	public ResponseEntity<HttpStatus> deleteHome(@PathVariable("id") long id) {
		try {
			homeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<HttpStatus> deleteAllHome() {
		try {
			homeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/notPublished")
	public ResponseEntity<List<Home>> findByPublished() {
		try {
			List<Home> tutorials = homeRepository.findByPublished(false);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/AllHomePublished")
	public ResponseEntity<List<Home>> AllHomePublished() {
		try {
			List<Home> tutorials = homeRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
