package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
public class ExchangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Long userId;

    @NotBlank
    private int  numeroTel;

    @NotBlank
    private String  username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String  email;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date debutHeberg;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endHeberg;

    @NotBlank
    private int  annonceNum;

    @NotBlank
    private String sujet;

    @NotBlank
    private String message;

    public ExchangeRequest(Long id, @NotBlank Long userId, @NotBlank int numeroTel, @NotBlank String username, @NotBlank @Size(max = 50) @Email String email, Date debutHeberg, Date endHeberg, @NotBlank int annonceNum, @NotBlank String sujet, @NotBlank String message) {
        this.id = id;
        this.userId = userId;
        this.numeroTel = numeroTel;
        this.username = username;
        this.email = email;
        this.debutHeberg = debutHeberg;
        this.endHeberg = endHeberg;
        this.annonceNum = annonceNum;
        this.sujet = sujet;
        this.message = message;
    }

    public ExchangeRequest() {
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

    public int getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(int numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDebutHeberg() {
        return debutHeberg;
    }

    public void setDebutHeberg(Date debutHeberg) {
        this.debutHeberg = debutHeberg;
    }


    public Date getEndHeberg() {
        return endHeberg;
    }

    public void setEndHeberg(Date endHeberg) {
        this.endHeberg = endHeberg;
    }

    public int getAnnonceNum() {
        return annonceNum;
    }

    public void setAnnonceNum(int annonceNum) {
        this.annonceNum = annonceNum;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExchangeRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", numeroTel=" + numeroTel +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", debutHeberg=" + debutHeberg +
                ", endHeberg=" + endHeberg +
                ", annonceNum=" + annonceNum +
                ", sujet='" + sujet + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
