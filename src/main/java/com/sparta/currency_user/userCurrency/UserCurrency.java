package com.sparta.currency_user.userCurrency;

import com.sparta.currency_user.entity.BaseEntity;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class UserCurrency extends BaseEntity {
    //환전 요청 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //환전 전 금액
    private BigDecimal amountInKrw;

    //환전 후 금액
    private BigDecimal amountAfterExchange;

    //상태
    private String status;

    public UserCurrency(BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status) {
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    //기본생성자
    public UserCurrency() {}

    //고객 고유 식별자
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    //환전 대상 통화 식별자
    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    Currency currency;
}
