package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Delivery;
import com.rsreu.bestProject.data.jpa.DeliveryRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import com.rsreu.bestProject.dto.delivery.DeliveryDTO;
import com.rsreu.bestProject.dto.delivery.request.AddDeliveryDTORequest;
import com.rsreu.bestProject.enums.AnalyzeMessageType;
import com.rsreu.bestProject.util.AnalyzeUtil;
import com.rsreu.bestProject.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    private final Analyser analyser;

    public DeliveryDTO add(AddDeliveryDTORequest deliveryDto){
        Delivery delivery = new Delivery()
                .setDeliveryType(deliveryDto.getDeliveryType())
                .setId(deliveryDto.getId())
                .setAdressFrom(deliveryDto.getAdressFrom())
                .setAdressTo(deliveryDto.getAdressTo())
                .setDate(OffsetDateTime.of(LocalDateTime.ofEpochSecond(deliveryDto.getDate(), 0, ZoneOffset.UTC), ZoneOffset.UTC))
                .setPeriod(deliveryDto.getPeriod());

        var product = productRepository.findById(deliveryDto.getProductId()).orElse(null);
        delivery.setProduct(product);

        var farmer = userRepository.findById(deliveryDto.getFarmerId()).orElse(null);
        delivery.setFarmer(farmer);

        var consumer = userRepository.findById(deliveryDto.getConsumerId()).orElse(null);
        delivery.setConsumer(consumer);
        deliveryRepository.save(delivery);

        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapDeliveryToAnalyze(delivery), AnalyzeMessageType.ADD));

        return dtoMapper.mapDeliveryToDto(delivery);
    }

    public boolean delete(Long id){
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        if(delivery != null){
            deliveryRepository.delete(delivery);
            analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapDeliveryToAnalyze(delivery), AnalyzeMessageType.REMOVE));
            return true;
        }
        return false;
    }

    public DeliveryDTO update(AddDeliveryDTORequest deliveryDto){
        var delivery = deliveryRepository.findById(deliveryDto.getId()).get();
        delivery.setDeliveryType(deliveryDto.getDeliveryType())
                .setId(deliveryDto.getId())
                .setAdressFrom(deliveryDto.getAdressFrom())
                .setAdressTo(deliveryDto.getAdressTo())
                .setDate(OffsetDateTime.of(LocalDateTime.ofEpochSecond(deliveryDto.getDate(), 0, ZoneOffset.UTC), ZoneOffset.UTC))
                .setPeriod(deliveryDto.getPeriod());
        deliveryRepository.save(delivery);
        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapDeliveryToAnalyze(delivery), AnalyzeMessageType.UPDATE));
        return dtoMapper.mapDeliveryToDto(delivery);
    }
}
