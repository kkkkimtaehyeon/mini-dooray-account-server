package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findById(long id);
}
