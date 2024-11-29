package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    //C:환전 요청 수행
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> exchange(@RequestBody ExchangeRequestDto exchangeRequestDto) {
        ExchangeResponseDto responseDto = exchangeService.exchange(exchangeRequestDto.getCurrencyId(), exchangeRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //R: 특정 고객 이 수행한 환전 요청 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> getExchangesByUserId(@PathVariable Long userId) {
        List<ExchangeResponseDto> exchangeResponseDtos = exchangeService.getExchangesByUserId(userId);
        return new ResponseEntity<>(exchangeResponseDtos, HttpStatus.OK);
    }

    //U:환전 요청 상태를 취소로 변경
    @PutMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> cancelExchange(@PathVariable Long id) {
        ExchangeResponseDto responseDto = exchangeService.cancelExchange(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
