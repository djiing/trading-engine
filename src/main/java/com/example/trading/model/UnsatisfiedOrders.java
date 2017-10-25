package com.example.trading.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Queue;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Getter
@ToString
@EqualsAndHashCode
public class UnsatisfiedOrders {

    @Id
    @JsonInclude(Include.NON_NULL)
    private String id;
    @JsonProperty("unsatisfiedOrders")
    private Queue<Order> orders;

    UnsatisfiedOrders(Queue<Order> orders) {
        this.orders = orders;
    }

}
