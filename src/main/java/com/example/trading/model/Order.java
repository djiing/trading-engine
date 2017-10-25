package com.example.trading.model;

import lombok.*;

import java.math.BigDecimal;

@Value
public class Order {
    OrderType type;
    long timestamp;
    BigDecimal price;
    String party;
    PartyType partyType;
}
