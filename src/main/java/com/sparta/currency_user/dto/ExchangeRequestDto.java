package com.sparta.currency_user.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    private Long userId;

    private BigDecimal amountInKrw;

    private Long currencyId;

    public ExchangeRequestDto(Long userId, BigDecimal amountInKrw, Long currencyId) {
        this.userId = userId;
        this.amountInKrw = amountInKrw;
        this.currencyId = currencyId;
    }
}
