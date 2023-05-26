package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.data.jpa.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    public void add(TemplateEntity entity){
        templateRepository.save(entity);
    }

    public void delete(TemplateEntity entity){
        templateRepository.delete(entity);
    }

    public void edit(TemplateEntity entity){
        templateRepository.save(entity);
    }
}
