package com.mysite.finalProject.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id는 자동생성됨
    private Long id;

    //회원가입 시 id
    @Column(name = "username", unique = true,nullable = false,length = 100)
    private String username;

    //회원가입 시 password
    @Column(name = "password", nullable = false)
    private String password;

    //회원가입 시 이름
    @Column(name = "name",nullable = false)
    private String name;

    //회원가입 시 주소
    @Column(name = "address",nullable =true)
    private String address;

    //생성 일자
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    //권한
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;

    @Transient
    private String token; //DB에 저장되지 않음
}
