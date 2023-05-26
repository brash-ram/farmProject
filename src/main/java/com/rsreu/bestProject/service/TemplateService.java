package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.TemplateEntity;
import com.rsreu.bestProject.data.jpa.TemplateRepository;
import com.rsreu.bestProject.dto.template.TemplateDTO;
import com.rsreu.bestProject.dto.template.request.TemplateDTORequest;
import com.rsreu.bestProject.util.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final DtoMapper dtoMapper;

    public void add(TemplateDTORequest request){
        TemplateEntity entity = new TemplateEntity();
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

    public TemplateDTO update(TemplateDTORequest template){
        var entity = templateRepository.findById(0L).orElse(null);
        //entity.set..
        return dtoMapper.mapTemlateDto(entity);
    }

}
