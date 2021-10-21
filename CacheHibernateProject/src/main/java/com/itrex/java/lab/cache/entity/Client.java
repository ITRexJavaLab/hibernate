package com.itrex.java.lab.cache.entity;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="client", schema = "public")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Integer age;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "age = " + age + ")";
    }
}
