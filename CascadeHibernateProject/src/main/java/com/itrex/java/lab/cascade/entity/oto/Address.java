package com.itrex.java.lab.cascade.entity.oto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="address", schema = "public")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User account;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}
