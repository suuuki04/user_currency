#유저 테이블 생성하는 쿼리
CREATE TABLE user
(
    id    bigint auto_increment primary key,
    email varchar(255) null,
    name  varchar(255) null
);

# 환전 테이블 생성하는 쿼리
CREATE TABLE currency
(
    exchange_rate decimal(38, 2) null,
    id            bigint auto_increment primary key,
    currency_name varchar(255)   null,
    symbol        varchar(255)   null
);

#유저와 환전 중간 테이블 생성하는 쿼리
CREATE TABLE exchange
(
    amount_after_exchange decimal(38, 2) null,
    amount_int_krw        decimal(38, 2) null,
    id                    bigint auto_increment primary key,
    to_currency_id        bigint         null,
    user_id               bigint         null,
    status                varchar(255)   null,
    constraint FK_user foreign key (user_id) references user (id),
    constraint FK_currency foreign key (to_currency_id) references currency (id)
);

#환전 상태 취소로 변경
UPDATE exchange
SET status = 'cancelled'
WHERE id = ? AND status = 'normal';

#유저 삭제
DELETE FROM exchange
WHERE user_id = ?;

