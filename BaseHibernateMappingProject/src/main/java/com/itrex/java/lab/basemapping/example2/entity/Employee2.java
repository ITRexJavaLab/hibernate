package com.itrex.java.lab.basemapping.example2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee", schema = "public")
public class Employee2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    /**
     * for ENUM mapping, best practice to add EnumType for appropriate type in DB
     * (String and Ordinal)
     * */
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    public Employee2(String name) {
        this.name = name;
    }

    public Employee2(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

}
