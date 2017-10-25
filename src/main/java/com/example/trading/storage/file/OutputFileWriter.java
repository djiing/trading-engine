package com.example.trading.storage.file;

import com.example.trading.model.MatchedOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Log
public class OutputFileWriter {

    @Value("${output.file}")
    private String outputFileName;

    private final ObjectMapper objectMapper;

    public OutputFileWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void write(List<MatchedOrder> orders){
        try {
            objectMapper.writeValue(new File(outputFileName), orders);
        } catch (IOException e) {
            log.severe("The output file could not be written.");
        }
    }
}
