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

    public boolean delete(Long id){
        TemplateEntity template = templateRepository.findById(id).orElse(null);
        if(template != null){
            templateRepository.delete(template);
            return true;
        }
        return false;

    }

    public void edit(TemplateEntity entity){
        templateRepository.save(entity);
    }
}
