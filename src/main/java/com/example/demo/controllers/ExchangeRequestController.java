package com.example.demo.controllers;


import com.example.demo.models.ExchangeRequest;
import com.example.demo.models.Home;
import com.example.demo.repository.ExchangeRequestRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.Oneway;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/homeExchange")
public class ExchangeRequestController {
    @Autowired
    ExchangeRequestRepository exchangeRequestRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/allRequest")
    public List<ExchangeRequest> requestList() {

        List<ExchangeRequest> allRequest= exchangeRequestRepository.findAll();

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
    public ResponseEntity<ExchangeRequest> createHome(@RequestParam("numeroTel") int numeroTel,@RequestParam("username") String username,
                                           @RequestParam("email") String  email,@RequestParam("annonceNum") int annonceNum,
                                           @RequestParam("sujet") String sujet, @RequestParam("message") String message,
                                           @RequestParam("debutHeberg") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date debutHeberg,
                                           @RequestParam("endHeberg") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date endHeberg)
    { try
         {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Long myUser=userRepository.findByUsername(currentUserName).get().getId();

        ExchangeRequest home = new ExchangeRequest();
        home.setNumeroTel(numeroTel);
        home.setUsername(username);
        home.setEmail(email);
        home.setAnnonceNum(annonceNum);
        home.setSujet(sujet);
        home.setMessage(message);
        home.setDebutHeberg(debutHeberg);
        home.setEndHeberg(endHeberg);
        home.setUserId(myUser);
        exchangeRequestRepository.save(home);
        return new ResponseEntity<>(home, HttpStatus.CREATED);
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
    public ResponseEntity<HttpStatus> deleteAllHome() {
        try {
            exchangeRequestRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
