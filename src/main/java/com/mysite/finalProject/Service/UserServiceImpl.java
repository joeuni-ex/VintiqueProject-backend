package com.mysite.finalProject.Service;

import com.mysite.finalProject.model.Role;
import com.mysite.finalProject.model.User;
import com.mysite.finalProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    //@RequiredArgsConstructor 사용
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;//비밀번호 암호화


    @Override
    //유저 저장하기
    public User saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    //유저 검색하기
    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    //유저 권한 업데이트하기
    @Override
    @Transactional //update,delete 쿼리에 적용하여 안정성 보장
    public void changeRole(Role newRole, String username){
        userRepository.updateUserRole(username,newRole);
    }
}
