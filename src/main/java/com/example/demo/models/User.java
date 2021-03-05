package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size(max = 20)
        private String username;

        @NotBlank
        @Size(max = 20)
        private String lastName;

        @NotNull
        @NotBlank(message="Please enter your phone number")
        private String phoneNumber;


        private String phoneNumber2;

        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
        private Date debutDispo;


        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
        private Date endDispo;

        @NotBlank
        @Size(max = 120)
        private String region;

        private String facebook;

        private String destination;

        @NotBlank
        @Size(max = 50)
        @Email
        private String email;

        @NotBlank
        @Size(max = 120)
        private String password;


        @NotBlank
        private String cin;

        @NotBlank
        private String steg;


        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(	name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public User() {
        }

        //
        public User(String username, String email, String password) {
                this.username = username;
                this.email = email;
                this.password = password;
        }

        //constructor with all fields
        public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 20) String lastName, @NotNull @NotBlank(message = "Please enter your phone number") String phoneNumber, String phoneNumber2, Date debutDispo, Date endDispo, @NotBlank @Size(max = 120) String region, String facebook, String destination, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, @NotBlank String cin, @NotBlank String steg, Set<Role> roles) {
                this.id = id;
                this.username = username;
                this.lastName = lastName;
                this.phoneNumber = phoneNumber;
                this.phoneNumber2 = phoneNumber2;
                this.debutDispo = debutDispo;
                this.endDispo = endDispo;
                this.region = region;
                this.facebook = facebook;
                this.destination = destination;
                this.email = email;
                this.password = password;
                this.cin = cin;
                this.steg = steg;
                this.roles = roles;
        }

        //constructor without facebook disponibilite and phone number 2
        public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 20) String lastName, @NotNull @NotBlank(message = "Please enter your phone number") String phoneNumber, @NotBlank @Size(max = 120) String region, String destination, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, @NotBlank String cin, @NotBlank String steg, Set<Role> roles) {
                this.id = id;
                this.username = username;
                this.lastName = lastName;
                this.phoneNumber = phoneNumber;
                this.region = region;
                this.destination = destination;
                this.email = email;
                this.password = password;
                this.cin = cin;
                this.steg = steg;
                this.roles = roles;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getPhoneNumber2() {
                return phoneNumber2;
        }

        public void setPhoneNumber2(String phoneNumber2) {
                this.phoneNumber2 = phoneNumber2;
        }

        public String getFacebook() {
                return facebook;
        }

        public void setFacebook(String facebook) {
                this.facebook = facebook;
        }

        public String getDestination() {
                return destination;
        }

        public void setDestination(String destination) {
                this.destination = destination;
        }

        public String getRegion() {
                return region;
        }

        public void setRegion(String region) {
                this.region = region;
        }

        public Set<Role> getRoles() {
                return roles;
        }

        public void setRoles(Set<Role> roles) {
                this.roles = roles;
        }

        public Date getDebutDispo() {
                return debutDispo;
        }

        public void setDebutDispo(Date debutDispo) {
                this.debutDispo = debutDispo;
        }

        public Date getEndDispo() {
                return endDispo;
        }

        public void setEndDispo(Date endDispo) {
                this.endDispo = endDispo;
        }

        public String getCin() {
                return cin;
        }

        public void setCin(String cin) {
                this.cin = cin;
        }

        public String getSteg() {
                return steg;
        }

        public void setSteg(String steg) {
                this.steg = steg;
        }
}
