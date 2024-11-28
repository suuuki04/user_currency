package com.sparta.currency_user.entity;

import com.sparta.currency_user.userCurrency.UserCurrency;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}

    @OneToMany
    @JoinColumn(name = "userId")
    List<UserCurrency> userCurrencyList;

}