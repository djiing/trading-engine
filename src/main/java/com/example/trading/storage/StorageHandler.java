package com.example.trading.storage;

import com.example.trading.model.ProcessingResult;
import com.example.trading.storage.file.OutputFileWriter;
import com.example.trading.storage.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class StorageHandler {

    private final OutputFileWriter outputFileWriter;
    private final OrderRepository orderRepository;

    public StorageHandler(OutputFileWriter outputFileWriter, OrderRepository orderRepository) {
        this.outputFileWriter = outputFileWriter;
        this.orderRepository = orderRepository;
    }

    public void save(ProcessingResult processingResult){
        outputFileWriter.write(processingResult.getMatchedOrders());

        //TODO This would have been used when a new batch arrived for processing and we would check the unsatisfied buyers again as first in line
        if (processingResult.buyersRemaining() ){
            orderRepository.save(processingResult.returnUnsatisfiedOrders());
        }
    }
}
