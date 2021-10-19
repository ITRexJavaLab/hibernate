package com.itrex.java.lab.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirthaa) {
        this.dateOfBirth = dateOfBirthaa;
    }

    @Override
    public String toString() {
        return "\nUser{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", email='" + email + '\'' +
          ", dateOfBirth=" + dateOfBirth +
          "}";
    }
}
