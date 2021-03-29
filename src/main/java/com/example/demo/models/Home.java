package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data

@Getter
@Setter
@Entity
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    
    private String title;

    private int  etages;

    private int  Lits;
    private int  balcons;
    private int  terrasses;
    private int  bains;
    private int  chambres;
    private String logement;
    private String region;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date debut;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date end;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<FileDB> photos;

    @Column(name = "published")
    private boolean published;

    private boolean wifi;
    private boolean TV;
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

  
}
