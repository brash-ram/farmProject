package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {


}
