package com.itrex.java.lab.cache.entity.batch;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="client", schema = "public")
public class Client3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Integer age;

    @OneToMany(mappedBy = "client")
    @BatchSize(size = 4)
    private List<Account3> accounts = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "age = " + age + ")";
    }
}
