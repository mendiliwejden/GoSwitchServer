package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data

@Getter
@Setter
@Entity
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int  etages;

    private int  Lits;
    private int  balcons;
    private int  terrasses;
    private int  bains;
    private int  chambres;
    private String logement;
    private String adresse;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date debut;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date end;

    private String photo;

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

    public Home() {
    }

    public Home(Long id, Long userId, int etages, int lits, int balcons,
                int terrasses, int bains, int chambres, String logement,
                String adresse, Date debut, Date end,
                boolean published) {
        this.id = id;
        this.userId = userId;
        this.etages = etages;
        Lits = lits;
        this.balcons = balcons;
        this.terrasses = terrasses;
        this.bains = bains;
        this.chambres = chambres;
        this.logement = logement;
        this.adresse = adresse;
        this.debut = debut;
        this.end = end;
        this.published = published;
    }

    public Home(Long id, Long userId, int etages, int lits, int balcons,
                int terrasses, int bains, int chambres, String logement,
                String adresse, Date debut, Date end, String photo,
                boolean published) {
        this.id = id;
        this.userId = userId;
        this.etages = etages;
        Lits = lits;
        this.balcons = balcons;
        this.terrasses = terrasses;
        this.bains = bains;
        this.chambres = chambres;
        this.logement = logement;
        this.adresse = adresse;
        this.debut = debut;
        this.end = end;
        this.photo = photo;
        this.published = published;
    }

    public Home(Long id, Long userId, int etages, int lits, int balcons, int terrasses,
                int bains, int chambres, String logement, String adresse, Date debut,
                Date end, String photo, boolean published, boolean wifi, boolean TV,
                boolean microondes, boolean refrigrrateur, boolean escalier, boolean jacuzzi,
                boolean magazin, boolean piscine, boolean gazon, boolean animaux,
                boolean radio, boolean equipepourenfants, boolean bicyclette,
                boolean parking, boolean fumeurs) {
        this.id = id;
        this.userId = userId;
        this.etages = etages;
        Lits = lits;
        this.balcons = balcons;
        this.terrasses = terrasses;
        this.bains = bains;
        this.chambres = chambres;
        this.logement = logement;
        this.adresse = adresse;
        this.debut = debut;
        this.end = end;
        this.photo = photo;
        this.published = published;
        this.wifi = wifi;
        this.TV = TV;
        this.microondes = microondes;
        this.refrigrrateur = refrigrrateur;
        this.escalier = escalier;
        this.jacuzzi = jacuzzi;
        this.magazin = magazin;
        this.piscine = piscine;
        this.gazon = gazon;
        this.animaux = animaux;
        this.radio = radio;
        this.equipepourenfants = equipepourenfants;
        this.bicyclette = bicyclette;
        this.parking = parking;
        this.fumeurs = fumeurs;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getEtages() {
        return etages;
    }

    public void setEtages(int etages) {
        this.etages = etages;
    }

    public int getLits() {
        return Lits;
    }

    public void setLits(int lits) {
        Lits = lits;
    }

    public int getBalcons() {
        return balcons;
    }

    public void setBalcons(int balcons) {
        this.balcons = balcons;
    }

    public int getTerrasses() {
        return terrasses;
    }

    public void setTerrasses(int terrasses) {
        this.terrasses = terrasses;
    }

    public int getBains() {
        return bains;
    }

    public void setBains(int bains) {
        this.bains = bains;
    }

    public int getChambres() {
        return chambres;
    }

    public void setChambres(int chambres) {
        this.chambres = chambres;
    }

    public String getLogement() {
        return logement;
    }

    public void setLogement(String logement) {
        this.logement = logement;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isTV() {
        return TV;
    }

    public void setTV(boolean TV) {
        this.TV = TV;
    }

    public boolean isMicroondes() {
        return microondes;
    }

    public void setMicroondes(boolean microondes) {
        this.microondes = microondes;
    }

    public boolean isRefrigrrateur() {
        return refrigrrateur;
    }

    public void setRefrigrrateur(boolean refrigrrateur) {
        this.refrigrrateur = refrigrrateur;
    }

    public boolean isEscalier() {
        return escalier;
    }

    public void setEscalier(boolean escalier) {
        this.escalier = escalier;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public boolean isMagazin() {
        return magazin;
    }

    public void setMagazin(boolean magazin) {
        this.magazin = magazin;
    }

    public boolean isPiscine() {
        return piscine;
    }

    public void setPiscine(boolean piscine) {
        this.piscine = piscine;
    }

    public boolean isGazon() {
        return gazon;
    }

    public void setGazon(boolean gazon) {
        this.gazon = gazon;
    }

    public boolean isAnimaux() {
        return animaux;
    }

    public void setAnimaux(boolean animaux) {
        this.animaux = animaux;
    }

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public boolean isEquipepourenfants() {
        return equipepourenfants;
    }

    public void setEquipepourenfants(boolean equipepourenfants) {
        this.equipepourenfants = equipepourenfants;
    }

    public boolean isBicyclette() {
        return bicyclette;
    }

    public void setBicyclette(boolean bicyclette) {
        this.bicyclette = bicyclette;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isFumeurs() {
        return fumeurs;
    }

    public void setFumeurs(boolean fumeurs) {
        this.fumeurs = fumeurs;
    }
}
