package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Home;
import com.example.demo.models.dto.HomeDTO;
import com.example.demo.repository.HomeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.services.FileStorageService;
import com.example.demo.security.services.HomeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/home")
public class HomeController {

	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	HomeRepository homeRepository;

	HomeService homeService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FileStorageService fileStorageService;

	@GetMapping("/allHome")
	public ResponseEntity<List<Home>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Home> homes = new ArrayList<Home>();

			if (title == null)
				homeRepository.findAll().forEach(homes::add);
//            else
//                homeRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (homes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(homes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getHome/{id}")
	public ResponseEntity<Home> getHomeById(@PathVariable("id") long id) {
		Optional<Home> home = homeRepository.findById(id);

		if (home.isPresent()) {
			return new ResponseEntity<>(home.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addHome")
	public ResponseEntity<Home> createHome(@RequestParam("file") MultipartFile file, @RequestParam("home") String homee)
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

			// store image
			InputStream inputStream = file.getInputStream();
			String originalName = file.getOriginalFilename();
			String name = file.getName();
			String contentType = file.getContentType();
			long size = file.getSize();
			logger.info("inputStream: " + inputStream);
			logger.info("originalName: " + originalName);
			logger.info("name: " + name);
			logger.info("contentType: " + contentType);
			logger.info("size: " + size);

			if (file != null) {
				System.out.println("file is here");
				String fileDB = fileStorageService.store(file).getId();
				home.setPhoto(fileDB);
			}

			homeRepository.save(home);
			return new ResponseEntity<>(home, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/updateHomePublished/{id}")
	public ResponseEntity<Home> updateHomePublished(@PathVariable("id") long id) throws IOException {
		// home passed
		Optional<Home> homeData = homeRepository.findById(id);

		if (homeData.isPresent()) {
			Home _home = homeData.get();

			Home home1 = new Home();
			home1.setEtages(_home.getEtages());
			home1.setTitle(_home.getTitle());
			home1.setLits(_home.getLits());
			home1.setBalcons(_home.getBalcons());
			home1.setTerrasses(_home.getTerrasses());
			home1.setBains(_home.getBains());
			home1.setChambres(_home.getChambres());
			home1.setLogement(_home.getLogement());
			home1.setRegion(_home.getRegion());
			home1.setDebut(_home.getDebut());
			home1.setEnd(_home.getEnd());
			home1.setUserId(_home.getUserId());
			home1.setPublished(true);
			home1.setPhoto(_home.getPhoto());
			Home result = homeRepository.save(home1);
			homeRepository.deleteById(id);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
	
	@GetMapping("/HomeListByRegion/{region}")
	public ResponseEntity<List<Home>> getHomeListByRegion(@PathVariable("region") String region) {
		List<Home> homeList = homeRepository.findByRegion(region);

		if (homeList != null) {
			return new ResponseEntity<>(homeList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
