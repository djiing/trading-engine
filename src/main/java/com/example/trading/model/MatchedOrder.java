package com.example.trading.model;

import lombok.Value;

@Value
public class MatchedOrder {

    Order buy;
    Order sell;
}
