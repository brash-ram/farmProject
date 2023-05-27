package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Delivery;
import com.rsreu.bestProject.data.entity.Product;
import com.rsreu.bestProject.data.jpa.DeliveryRepository;
import com.rsreu.bestProject.data.jpa.ProductRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import com.rsreu.bestProject.dto.delivery.DeliveryDTO;
import com.rsreu.bestProject.dto.delivery.request.AddDeliveryDTORequest;
import com.rsreu.bestProject.enums.AnalyzeMessageType;
import com.rsreu.bestProject.util.AnalyzeUtil;
import com.rsreu.bestProject.dto.delivery.request.UpdateDeliveryDTORequest;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.util.DateUtils;
import com.rsreu.bestProject.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .setAdressFrom(deliveryDto.getAdressFrom())
                .setAdressTo(deliveryDto.getAdressTo())
                .setDate(OffsetDateTime.of(LocalDateTime.ofEpochSecond(deliveryDto.getDate(), 0, ZoneOffset.UTC), ZoneOffset.UTC))
                .setPeriod(deliveryDto.getPeriod())
                .setPaymentType(deliveryDto.getPaymentType())
                .setCount(deliveryDto.getCount());

        var product = productRepository.findById(deliveryDto.getProductId()).orElse(null);
        delivery.setProduct(product);

        var farmer = userRepository.findById(deliveryDto.getFarmerId()).orElse(null);
        delivery.setFarmer(farmer);

        var consumer = userRepository.findById(deliveryDto.getConsumerId()).orElse(null);
        delivery.setConsumer(consumer);
        deliveryRepository.save(delivery);

        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapDeliveryToAnalyze(delivery, product), AnalyzeMessageType.ADD));

        return dtoMapper.mapDeliveryToDto(delivery);
    }

    public boolean delete(Long id){
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        if(delivery != null){
            deliveryRepository.delete(delivery);
            analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapDeliveryToAnalyze(delivery, delivery.getProduct()), AnalyzeMessageType.REMOVE));
            return true;
        }
        return false;
    }

    public DeliveryDTO update(UpdateDeliveryDTORequest deliveryDto){
        var delivery = deliveryRepository.findById(deliveryDto.getId()).get();
        delivery.setDeliveryType(deliveryDto.getDeliveryType())
                .setAdressFrom(deliveryDto.getAdressFrom())
                .setAdressTo(deliveryDto.getAdressTo())
                .setDate(DateUtils.parse(deliveryDto.getDate()))
                .setPeriod(deliveryDto.getPeriod())
                .setDeliveryType(deliveryDto.getDeliveryType());;
        deliveryRepository.save(delivery);
        analyser.send(AnalyzeUtil.getMessage(dtoMapper.mapDeliveryToAnalyze(delivery, delivery.getProduct()), AnalyzeMessageType.UPDATE));

        return dtoMapper.mapDeliveryToDto(delivery);
    }

    public Map<Long, List<ProductDTO>> getDeliveries(Long userId){
        var user = userRepository.findById(userId).orElse(null);
        List<Delivery> listDeliveries = null;
        Map<Long, List<ProductDTO>> deliveryCalender = new HashMap<>();
        if(user != null){
            listDeliveries = deliveryRepository.findAllByConsumer(user);
        }

        if(listDeliveries != null){
            for (var el:listDeliveries) {
                deliveryCalender.put(el.getDate().toEpochSecond(), new ArrayList<>());
            }

            for (var el:listDeliveries) {
                deliveryCalender.get(el.getDate().toEpochSecond()).add(dtoMapper.mapProductToDTO(el.getProduct()));
            }

        }

        return deliveryCalender;

    }

    public List<DeliveryDTO> search(String position) {
        List<Delivery> deliveries = deliveryRepository.findAllByProductPosition(position);
        return deliveries.stream().map(dtoMapper::mapDeliveryToDto).toList();
    }

    public DeliveryDTO getDeliveryDtoById(Long id) {
        return dtoMapper.mapDeliveryToDto(deliveryRepository.findById(id).orElse(null));
    }

    public List<DeliveryDTO> getAll() {
        return deliveryRepository.findAll().stream().map(dtoMapper::mapDeliveryToDto).toList();
    }

    public List<DeliveryDTO> addAll(List<AddDeliveryDTORequest> request) {
        List<DeliveryDTO> results = new ArrayList<>();
        request.forEach(req -> results.add(add(req)));
        return results;
    }
}
