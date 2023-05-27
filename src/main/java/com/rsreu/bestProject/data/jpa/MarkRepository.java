package com.rsreu.bestProject.data.jpa;

import com.rsreu.bestProject.data.entity.Mark;
import com.rsreu.bestProject.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    Optional<Mark> findByFromAndTo(UserInfo from, UserInfo to);

    List<Mark> findAllByTo(UserInfo to);
}