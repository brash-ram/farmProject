package com.rsreu.bestProject.controller;

import com.rsreu.bestProject.dto.delivery.DeliveryDTO;
import com.rsreu.bestProject.dto.delivery.request.AddDeliveryDTORequest;
import com.rsreu.bestProject.dto.delivery.request.UpdateDeliveryDTORequest;
import com.rsreu.bestProject.dto.product.ProductDTO;
import com.rsreu.bestProject.service.DeliveryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/add")
    public ResponseEntity<DeliveryDTO> add(@RequestBody @Valid AddDeliveryDTORequest deliveryDTORequest) {
        var dto = deliveryService.add(deliveryDTORequest);
        return ResponseEntity
                .ok()
                .body(dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeliveryDTO> delete(@RequestParam Long id) {
        if(deliveryService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/update")
    public ResponseEntity<DeliveryDTO> update(@RequestBody UpdateDeliveryDTORequest request) {
        return ResponseEntity.ok(deliveryService.update(request));
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<DeliveryDTO>> addAll(@RequestBody @Valid List<AddDeliveryDTORequest> request) {
        return ResponseEntity.ok(deliveryService.addAll(request));
    }

    @GetMapping("/calender")
    public ResponseEntity<Map<Long, List<ProductDTO>>> getDelivery(@RequestParam Long id){
        return ResponseEntity.ok(deliveryService.getDeliveries(id));
    }

    @GetMapping("/getById")
    public ResponseEntity<DeliveryDTO> getById(@RequestParam Long id) {
        return ResponseEntity.ok(deliveryService.getDeliveryDtoById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DeliveryDTO>> getAll() {
        return ResponseEntity.ok(deliveryService.getAll());
    }

    @GetMapping("/search/position")
    public ResponseEntity<List<DeliveryDTO>> searchByPosition(@RequestParam @NotNull String position) {
        return ResponseEntity.ok(deliveryService.search(position));
    }
}
