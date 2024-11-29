package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Exchange;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeResponseDto {
    private Long id;

    private BigDecimal amountAfterExchange;

    private String status;


    public ExchangeResponseDto(Long id, BigDecimal amountAfterExchange, String status) {
        this.id = id;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }
}
