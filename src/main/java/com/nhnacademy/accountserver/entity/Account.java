package com.nhnacademy.accountserver.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    private String id;
    private String password;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Account(String id, String password, Member member) {
        this.id = id;
        this.password = password;
        this.member = member;
    }
}
