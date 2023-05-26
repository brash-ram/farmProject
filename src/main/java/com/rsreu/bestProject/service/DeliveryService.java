package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Delivery;
import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.data.jpa.DeliveryRepository;
import com.rsreu.bestProject.data.jpa.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository templateRepository;

    public void add(Delivery delivery){
        templateRepository.save(delivery);
    }

    public void delete(Delivery delivery){
        templateRepository.delete(delivery);
    }

    public void edit(Delivery delivery){
        templateRepository.save(delivery);
    }
}
