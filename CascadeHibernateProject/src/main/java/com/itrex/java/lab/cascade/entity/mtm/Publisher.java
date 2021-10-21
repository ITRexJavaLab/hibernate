package com.itrex.java.lab.cascade.entity.mtm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="publisher", schema = "public")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "publishers", cascade = CascadeType.ALL)
    private List<Newspaper> newspapers = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}
