package com.rsreu.bestProject.service;

import com.rsreu.bestProject.dto.analyse.AnalyzeMessage;
import com.rsreu.bestProject.dto.analyse.ProductAnalyzeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Analyser {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.queue}")
    private String queue;

    @Value("${rabbit.exchange}")
    private String exchange;

    public <T> void send(AnalyzeMessage<T> dto) {
        rabbitTemplate.convertAndSend(exchange, queue, dto);
    }

}
