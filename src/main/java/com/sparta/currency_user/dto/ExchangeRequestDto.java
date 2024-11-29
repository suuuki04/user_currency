package com.sparta.currency_user.dto;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


@Getter
public class ExchangeRequestDto {

    @NotNull(message = "고객 아이디는 필수입니다.")
    private Long userId;

    @NotNull(message = "금액을 입력해야 합니다.")
    private BigDecimal amountInKrw;

    @NotNull(message = "통화 아이디는 필수입니다.")
    private Long currencyId;

    public ExchangeRequestDto(Long userId, BigDecimal amountInKrw, Long currencyId) {
        this.userId = userId;
        this.amountInKrw = amountInKrw;
        this.currencyId = currencyId;
    }
}
