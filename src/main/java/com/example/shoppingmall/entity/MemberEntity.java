package com.example.shoppingmall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String UserId;

    @Column(length = 30, nullable = false)
    private String memberPassword;

    @Column(length = 30, nullable = false)
    private String memberEmail;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 15, nullable = false)
    private String memberMobile;

    @Column(length = 100)
    private String memberAddress;

}
