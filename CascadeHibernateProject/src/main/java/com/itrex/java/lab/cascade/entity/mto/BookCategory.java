package com.itrex.java.lab.cascade.entity.mto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="book_category", schema = "public")
public class BookCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "bookCategory")
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}
