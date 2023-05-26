package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.Delivery;
import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {


    List<Delivery> findAllByConsumer(UserInfo userInfo);
}
