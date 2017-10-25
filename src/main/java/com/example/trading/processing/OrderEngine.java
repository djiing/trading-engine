package com.example.trading.processing;

import com.example.trading.model.Order;
import com.example.trading.model.OrderRegistry;
import com.example.trading.model.ProcessingResult;
import com.example.trading.model.UnsatisfiedOrders;
import com.example.trading.processing.registry.OrderRegistryResolver;
import com.example.trading.storage.StorageHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Service
public class OrderEngine {

    private final OrderRegistryResolver orderRegistryResolver;
    private final StorageHandler storageHandler;

    public OrderEngine(OrderRegistryResolver orderRegistryResolver,
                       StorageHandler storageHandler) {
        this.orderRegistryResolver = orderRegistryResolver;
        this.storageHandler = storageHandler;
    }

    public UnsatisfiedOrders process(List<Order> orders) {
        OrderRegistry orderRegistry = orderRegistryResolver.accept(orders);
        ProcessingResult processingResult = matchOrders(orderRegistry);

        storageHandler.save(processingResult);

        return processingResult.returnUnsatisfiedOrders();
    }

    private ProcessingResult matchOrders(OrderRegistry orderRegistry) {
        ProcessingResult processingResult = new ProcessingResult();

        Queue<Order> buys = orderRegistry.getBuys();

        while (isNotEmpty(buys)) {

            Order buy = buys.poll();
            Queue<Order> sells = orderRegistry.getSells();

            if (noSellingOrderButBuyersExist(buys, sells)) {
                return processingResult.addUnsatisfiedOrders(buys);
            }

            Order sell = sells.peek();

            if (bothSellingOrderAndBuyersExist(buy, sell)) {
                sells.poll();
                processingResult.addMatchedOrder(buy, sell);
            } else {
                processingResult.addUnsatisfiedOrder(buy);
            }
        }

        return processingResult;
    }

    private boolean noSellingOrderButBuyersExist(Queue<Order> buys, Queue<Order> sells) {
        return sells.size() == 0 && buys.size() != 0;
    }

    private boolean bothSellingOrderAndBuyersExist(Order buy, Order sell) {
        return null != sell && sell.getPrice().compareTo(buy.getPrice()) <= 0;
    }

    private boolean isNotEmpty(Queue<Order> buys) {
        return !buys.isEmpty();
    }
}
