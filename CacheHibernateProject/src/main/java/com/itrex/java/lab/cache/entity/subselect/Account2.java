package com.itrex.java.lab.cache.entity.subselect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="account", schema = "public")
public class Account2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer amount;
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client2 client;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "amount = " + amount + ", " +
                "currency = " + currency + ")";
    }
}
