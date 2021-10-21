package com.itrex.java.lab.cache.entity.subselect;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="client", schema = "public")
public class Client2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Integer age;

    @OneToMany(mappedBy = "client")
    @Fetch(FetchMode.SUBSELECT)
    private List<Account2> accounts = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "age = " + age + ")";
    }
}
