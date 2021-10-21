package com.itrex.java.lab.cache.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Immutable
@Table(name="account", schema = "public")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer amount;
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Client client;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "amount = " + amount + ", " +
                "currency = " + currency + ")";
    }
}
