package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Exchange extends BaseEntity {
    //환전 요청 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //환전 전 금액
    private BigDecimal amountIntKrw;

    //환전 후 금액
    private BigDecimal amountAfterExchange;

    //상태
    private String status;

//    public Exchange(BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status) {
//        this.amountInKrw = amountInKrw;
//        this.amountAfterExchange = amountAfterExchange;
//        this.status = status;
//    }
//

    //고객 고유 식별자
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    //환전 대상 통화 식별자
    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    Currency currency;

    //기본생성자
    public Exchange() {}

    public Exchange(User user, Currency currency, BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status) {
        this.user = user;
        this.currency = currency;
        this.amountIntKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }
}
