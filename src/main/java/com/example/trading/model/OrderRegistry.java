package com.example.trading.model;

import lombok.Getter;

import java.util.*;

import static com.example.trading.model.OrderType.BUY;
import static com.example.trading.model.OrderType.SELL;

@Getter
public class OrderRegistry {

    private final Queue<Order> buys;
    private final Queue<Order> sells;

    public OrderRegistry(Map<OrderType, List<Order>> ordersByType) {
        this(ordersByType, null);
    }

    public OrderRegistry(Map<OrderType, List<Order>> ordersByType, Comparator<Order> comparator) {
        List<Order> buys = ordersByType.get(BUY);
        List<Order> sells = ordersByType.get(SELL);

        if(null != comparator) {
            buys.sort(comparator);
            sells.sort(comparator);
        }

        this.buys = new LinkedList<>(buys);
        this.sells = new LinkedList<>(sells);
    }
}
