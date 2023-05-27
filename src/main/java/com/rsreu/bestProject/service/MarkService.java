package com.rsreu.bestProject.service;

import com.rsreu.bestProject.data.entity.Mark;
import com.rsreu.bestProject.data.entity.UserInfo;
import com.rsreu.bestProject.data.jpa.MarkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkService {

    private final UserService userService;

    private final MarkRepository markRepository;

    @Transactional
    public void addMark(UserInfo from, Long toId, Integer valueMark) {
        UserInfo to = userService.getById(toId);
        Mark mark = new Mark().setMark(valueMark).setFrom(from).setTo(to);
        markRepository.save(mark);
    }

//    @Transactional
//    public void changeMark(UserInfo from, Long toId, Integer valueMark) {
//        UserInfo to = userService.getById(toId);
//        Mark mark = markRepository.setMark(valueMark).setFrom(from).setTo(to);
//        markRepository.save(mark);
//    }
//
//    @Transactional
//    public void deleteMark(Integer mark, Long targetId) {
//        UserInfo user = getById(targetId);
//        List<Integer> marks = user.getRating();
//        marks.remove(mark);
//        user.setRating(marks);
//    }
}
