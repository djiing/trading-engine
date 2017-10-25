package com.example.trading.processing.registry;

import com.example.trading.model.Order;
import com.example.trading.model.OrderRegistry;
import com.example.trading.model.OrderType;
import com.example.trading.processing.decider.OrderComparator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderRegistryResolver {

    private final OrderComparator orderComparator;

    public OrderRegistryResolver(OrderComparator orderComparator) {
        this.orderComparator = orderComparator;
    }

    public OrderRegistry accept(List<Order> orders) {
        return accept(orders, orderComparator);
    }

    private OrderRegistry accept(List<Order> orders, OrderComparator comparator) {
        Map<OrderType, List<Order>> ordersByType = orders.stream().collect(Collectors.groupingBy(Order::getType));

        return new OrderRegistry(ordersByType, comparator);
    }

}
