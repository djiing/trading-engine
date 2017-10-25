package com.example.trading.web;

import com.example.trading.model.Order;
import com.example.trading.model.UnsatisfiedOrders;
import com.example.trading.processing.OrderEngine;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradingController {

    private final OrderEngine orderEngine;

    public TradingController(OrderEngine orderEngine) {
        this.orderEngine = orderEngine;
    }

    @PostMapping(path = "/current-orders", consumes = MediaType.APPLICATION_JSON_VALUE )
    public UnsatisfiedOrders performOrders(@RequestBody final List<Order> incomingOrders) {
        return orderEngine.process(incomingOrders);
    }
}
