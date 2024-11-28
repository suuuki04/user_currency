package com.sparta.currency_user.entity;

import com.sparta.currency_user.userCurrency.UserCurrency;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public Currency() {}

    @OneToMany
    @JoinColumn(name = "toCurrencyId")
    List<UserCurrency> userCurrencyList;
}
