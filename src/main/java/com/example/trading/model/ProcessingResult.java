package com.example.trading.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@NoArgsConstructor
public class ProcessingResult {

    private Queue<Order> unsatisfiedOrdersQueue = new LinkedList<>();
    @Getter
    private List<MatchedOrder> matchedOrders = new LinkedList<>();

    public boolean addUnsatisfiedOrder(Order order) {
        // So that if it cannot we get an exception
        return unsatisfiedOrdersQueue.add(order);
    }

    public ProcessingResult addUnsatisfiedOrders(Queue<Order> orders) {
        this.unsatisfiedOrdersQueue = orders;
        return this;
    }

    public boolean addMatchedOrder(Order buy, Order sell) {
        return matchedOrders.add(new MatchedOrder(buy, sell));
    }

    public UnsatisfiedOrders returnUnsatisfiedOrders(){
        return new UnsatisfiedOrders(unsatisfiedOrdersQueue);
    }

    public boolean buyersRemaining(){
        return unsatisfiedOrdersQueue.size() != 0;
    }

}
