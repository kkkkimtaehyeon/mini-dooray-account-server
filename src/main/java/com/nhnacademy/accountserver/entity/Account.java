package com.nhnacademy.accountserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    private String id;
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Account(String id, String password, Member member) {
        this.id = id;
        this.password = password;
        this.member = member;
    }

    public Account(Long accountId, String id, String password, Member member) {
        this.accountId = accountId;
        this.id = id;
        this.password = password;
        this.member = member;
    }
}
