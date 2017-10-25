package com.example.trading.processing.decider;

import com.example.trading.model.Order;
import com.google.common.collect.ComparisonChain;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class OrderComparator implements Comparator<Order> {

    private final PartyTypeComparator partyTypeComparator;

    public OrderComparator(PartyTypeComparator partyTypeComparator) {
        this.partyTypeComparator = partyTypeComparator;
    }

    @Override
    public int compare(Order o1, Order o2) {
        return ComparisonChain.start()
                .compare(o1.getTimestamp(), o2.getTimestamp())
                .compare(o1.getPartyType(), o2.getPartyType(), partyTypeComparator)
                .compare(o1.getPrice(), o2.getPrice())
                .result();
    }
}
