package com.example.trading.processing.decider;

import com.example.trading.model.PartyType;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
class PartyTypeComparator implements Comparator<PartyType> {

    @Override
    public int compare(PartyType o1, PartyType o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}