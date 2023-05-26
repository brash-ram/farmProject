package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.dto.delivery.DeliveryDTO;
import com.rsreu.bestProject.dto.delivery.request.AddDeliveryDTORequest;
import com.rsreu.bestProject.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/add")
    public ResponseEntity<DeliveryDTO> add(@RequestBody AddDeliveryDTORequest deliveryDTORequest) {
        var dto = deliveryService.add(deliveryDTORequest);
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeliveryDTO> delete(@RequestParam Long id) {
        if(deliveryService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    public ResponseEntity<DeliveryDTO> update(@RequestBody AddDeliveryDTORequest request) {
        return ResponseEntity.ok(deliveryService.update(request));
    }

}
