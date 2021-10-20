package com.itrex.java.lab.basemapping.example3.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee3", schema = "public")
public class Employee3 extends BaseEntity<Long> {

    /**
     * constraints like on DB table, but these should be 100% appropriate to DB
     * */
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    /**
     * If this field is absence in DB
     * */
    @Transient
    private boolean adult;

    @Transient
    private LocalDate birthDate;

    @Transient
    /**
     * for mapping old java Date class via Hibernate to DB
     * */
    @Temporal(TemporalType.TIMESTAMP)
    private Date oldDate;

    /**
     * mark embedded entity
     * */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street"))
    })
    private Address workAddress;



    public boolean isAdult() {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

    public Employee3(String name, Gender gender, Address homeAddress, Address workAddress) {
        this.name = name;
        this.gender = gender;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public Employee3(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

}
