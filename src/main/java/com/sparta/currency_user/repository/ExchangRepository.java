package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangRepository extends JpaRepository<Exchange, Long> {

    List<Exchange> findByUserId(Long userId);

    //고객 삭제
    void deleteByUserId(Long userId);

}
