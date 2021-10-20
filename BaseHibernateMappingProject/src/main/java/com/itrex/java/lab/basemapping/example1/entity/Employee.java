package com.itrex.java.lab.basemapping.example1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is appropriate to entity in DB
 * javax.persistence.Entity - not hibernate
 */
@Entity
/**
 * Appropriate table in DB, name and schema are not required, but good practice, name is taking from class name
 * not required annotation
 * */
@Table(name = "employee", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    /**
     * Annotation that defines id field
     */
    @Id
    /**
     * AUTO - Hibernate defines one of the following 3 strategies (default IDENTITY)
     * IDENTITY - Id provided by DB table, if Id column is serial
     * SEQUENCE - sequence by DB table, for example +2
     * TABLE - another separate table from DB provided ID, one filed with table name, another with appropriate id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * appropriate column in DB, not required, but good practice, name taking from field name
     * find column in db by var name if @Column is absence
     */
    @Column(name = "name")
    private String name;

    public Employee(String name) {
        this.name = name;
    }

}
