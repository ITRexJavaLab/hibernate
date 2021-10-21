package com.itrex.java.lab.cascade.entity.mtm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="newspaper", schema = "public")
public class Newspaper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "newspaper_publisher_link",
            joinColumns = @JoinColumn(name = "newspaper_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private List<Publisher> publishers;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}
