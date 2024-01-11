package com.mysite.finalProject.service;

import com.mysite.finalProject.model.Role;
import com.mysite.finalProject.model.User;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface UserService {
    //유저 저장하기
    User saveUser(User user);

    //유저 검색하기
    Optional<User> findByUsername(String username);

    //유저 권한 업데이트하기
    @Transactional
    //update,delete 쿼리에 적용하여 안정성 보장
    void changeRole(Role newRole, String username);
}
