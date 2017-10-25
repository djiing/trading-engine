package com.example.trading.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartyType {
    MARKET_MAKER(0), COMMON(1);

    private int priority;

}
