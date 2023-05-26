package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.Role;
import com.rsreu.bestProject.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}