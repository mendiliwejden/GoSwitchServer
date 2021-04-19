package com.example.demo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ExchangeRequest;
import com.example.demo.repository.ExchangeRequestRepository;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/homeExchange")
public class ExchangeRequestController {
	@Autowired
	ExchangeRequestRepository exchangeRequestRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/allRequest")
	public List<ExchangeRequest> requestList() {

		List<ExchangeRequest> allRequest = exchangeRequestRepository.findAll();

		return allRequest;

	}

	@GetMapping("/getRequest/{id}")
	public ResponseEntity<ExchangeRequest> getRequestById(@PathVariable("id") long id) {

		Optional<ExchangeRequest> exchangeRequest = exchangeRequestRepository.findById(id);

		if (exchangeRequest.isPresent()) {
			return new ResponseEntity<>(exchangeRequest.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addRequest")
	public ResponseEntity<ExchangeRequest> createHome(@RequestBody ExchangeRequest exchangeRequest) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentUserName = authentication.getName();
			Long myUser = userRepository.findByUsername(currentUserName).get().getId();

			exchangeRequest.setUserId(myUser);
			ExchangeRequest res = exchangeRequestRepository.save(exchangeRequest);
			return new ResponseEntity<>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteRequest/{id}")
	public ResponseEntity<HttpStatus> deleteExchange(@PathVariable("id") long id) {
		try {
			exchangeRequestRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateExchange/{id}")
	public ResponseEntity<ExchangeRequest> updateExchange(@RequestBody ExchangeRequest exchangeRequest,
			@PathVariable("id") long id) throws IOException {

		Optional<ExchangeRequest> homeData = exchangeRequestRepository.findById(id);

		if (homeData.isPresent()) {
			ExchangeRequest _homeExchange = homeData.get();

			_homeExchange.setEndHeberg((exchangeRequest.getEndHeberg()));
			_homeExchange.setDebutHeberg(exchangeRequest.getDebutHeberg());
			_homeExchange.setMessage(exchangeRequest.getMessage());
			_homeExchange.setEmail(exchangeRequest.getEmail());
			_homeExchange.setUsername(exchangeRequest.getUsername());
			_homeExchange.setSujet(exchangeRequest.getSujet());
			_homeExchange.setAnnonceNum(exchangeRequest.getAnnonceNum());
			_homeExchange.setNumeroTel(exchangeRequest.getNumeroTel());

			return new ResponseEntity<>(exchangeRequestRepository.save(_homeExchange), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteAllRequest")
	public ResponseEntity<HttpStatus> deleteAllExchangeRequest() {
		try {
			exchangeRequestRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
