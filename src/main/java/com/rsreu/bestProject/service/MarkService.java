package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Mark;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.MarkRepository;
import com.rsreu.bestProject.data.jpa.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarkService {

    private final UserRepository userRepository;

    private final MarkRepository markRepository;

    @Transactional
    public void addMark(UserInfo from, Long toId, Integer valueMark) {
        UserInfo to = userRepository.findById(toId).get();
        Mark mark = new Mark().setMark(valueMark).setFrom(from).setTo(to);
        markRepository.save(mark);
    }

    @Transactional
    public void changeMark(UserInfo from, Long toId, Integer valueMark) {
        UserInfo to = userRepository.findById(toId).get();
        Mark mark = markRepository.findByFromAndTo(from, to).get().setMark(valueMark);
        markRepository.save(mark);
    }

    @Transactional
    public void deleteMark(UserInfo from, Long toId, Integer valueMark) {
        UserInfo to = userRepository.findById(toId).get();
        Mark mark = markRepository.findByFromAndTo(from, to).get();
        markRepository.delete(mark);
        markRepository.save(mark);
    }

    @Transactional
    public double getRating(UserInfo user) {
        List<Integer> rating = markRepository.findAllByTo(user).stream().map(Mark::getMark).toList();
        Integer sumMarks = rating.stream().reduce(Integer::sum).get();
        return (double)sumMarks / rating.size();
    }
}
