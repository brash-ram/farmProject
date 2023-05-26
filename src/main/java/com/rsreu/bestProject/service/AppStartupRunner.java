package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Role;
import com.rsreu.bestProject.data.jpa.RoleRepository;
import com.rsreu.bestProject.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String...args) {
        if (roleRepository.findAll().size() == 0) {
            roleRepository.save(new Role().setName(RoleEnum.ADMIN));
            roleRepository.save(new Role().setName(RoleEnum.USER));
        }

    }
}
