package com.mysite.finalProject.repository;


import com.mysite.finalProject.model.Role;
import com.mysite.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    //findBy + fieldName
    Optional<User> findByUsername(String username);

    //유저 이름으로 해당 유저의 권한(user,admin)을 업데이트해준다.
    @Modifying//update,insert,delete 시 @modifying이 붙음
    @Query("update User set role = :role where username = :username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);
}
