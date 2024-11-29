package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangRepository exchangRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    //환전 요청 수행
    @Transactional
    public ExchangeResponseDto exchange(Long currencyId, ExchangeRequestDto exchangeRequestDto ) {
        //유저 정보 확인
        User user = userRepository.findById(exchangeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        //통화 종류 확인
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new IllegalArgumentException(("환전할 통화 종류를 찾을 수 없습니다.")));

        //환전 전 금액과 환울
        BigDecimal amountIntKrw = exchangeRequestDto.getAmountInKrw();
        BigDecimal exchangeRate = currency.getExchangeRate();

        //환전 후 금액
        BigDecimal amountAfterExchange = amountIntKrw.divide(exchangeRate, 2, RoundingMode.HALF_UP);

        //환전 요청 상태
        Exchange exchange = new Exchange(user, currency, amountIntKrw, amountAfterExchange, "normal");

        //환전 요청 저장
        exchangRepository.save(exchange);


        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getAmountAfterExchange(),
                exchange.getStatus()
        );
    }

    //특정 고객이 수행한 환전 요청 조회
    public List<ExchangeResponseDto> getExchangesByUserId(Long userId) {
        List<Exchange> exchanges = exchangRepository.findByUserId(userId);

        return exchanges.stream()
                .map(exchange -> new ExchangeResponseDto(
                        exchange.getId(),
                        exchange.getAmountAfterExchange(),
                        exchange.getStatus()
                ))
                .collect(Collectors.toList());
    }

    //환전 상태 취소로 변경
    @Transactional

    public ExchangeResponseDto cancelExchange(Long id) {
        Exchange exchange = exchangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 환전 기록을 찾을 수 없습니다."));

        // 상태가 "normal"일 경우에만 변경
        if ("normal".equals(exchange.getStatus())) {
            exchange.setStatus("cancelled"); // 상태 변경
        } else {
            throw new IllegalArgumentException("이미 취소된 상태입니다.");
        }

        // 변경된 환전 기록을 ExchangeResponseDto로 변환하여 반환
        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getAmountAfterExchange(),
                exchange.getStatus()
        );
    }

    //고객 삭제
    @Transactional
    public void deleteExchangesByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        exchangRepository.deleteByUserId(userId);
    }
}