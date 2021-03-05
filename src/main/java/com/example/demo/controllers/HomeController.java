package com.example.demo.controllers;


import com.example.demo.models.Home;
import com.example.demo.models.User;
import com.example.demo.repository.FileDBRepository;
import com.example.demo.repository.HomeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.services.FileStorageService;
import com.example.demo.security.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/home")
public class HomeController {

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
    public ResponseEntity<Home> createHome(@RequestParam("file") MultipartFile imageFile, @RequestParam("etages") int etages,
                                           @RequestParam("Lits") int Lits, @RequestParam("balcons") int balcons,
                                           @RequestParam("terrasses") int terrasses, @RequestParam("bains") int bains, @RequestParam("chambres") int chambres,
                                           @RequestParam("logement") String logement, @RequestParam("adresse") String adresse,
                                           @RequestParam("debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date debut, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date end)
        { try
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
            Long myUser=userRepository.findByUsername(currentUserName).get().getId();

            Home home = new Home();
            home.setEtages(etages);
            home.setLits(Lits);
            home.setBalcons(balcons);
            home.setTerrasses(terrasses);
            home.setUserId(myUser);
            home.setBains(bains);
            home.setChambres(chambres);
            home.setLogement(logement);
            home.setAdresse(adresse);

            home.setDebut(debut);
            home.setEnd(end);
            home.setPublished(false);
            home.setWifi(false);
            home.setTV(false);

            //store image
            String fileDB= fileStorageService.store(imageFile).getId();
            home.setPhoto(fileDB);
            homeRepository.save(home);
                return new ResponseEntity<>(home, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/updateHomePublished/{id}")
    public ResponseEntity<Home> updateHomePublished(@PathVariable("id") long id) throws IOException {
        //home passed
        Optional<Home> homeData = homeRepository.findById(id);

        if (homeData.isPresent()) {
            Home _home = homeData.get();

            Home home1= new Home();
            home1.setEtages(_home.getEtages());
            home1.setLits(_home.getLits());
            home1.setBalcons(_home.getBalcons());
            home1.setTerrasses(_home.getTerrasses());
            home1.setBains(_home.getBains());
            home1.setChambres(_home.getChambres());
            home1.setLogement(_home.getLogement());
            home1.setAdresse(_home.getAdresse());
            home1.setDebut(_home.getDebut());
            home1.setEnd(_home.getEnd());
            home1.setUserId(_home.getUserId());
            home1.setPublished(true);
            home1.setPhoto(_home.getPhoto());
            Home result= homeRepository.save(home1);
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
}
