package com.itrex.java.lab.cache.entity.batch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="account", schema = "public")
public class Account3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer amount;
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client3 client;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "amount = " + amount + ", " +
                "currency = " + currency + ")";
    }
}
