package com.nhnacademy.accountserver.respository;

import com.nhnacademy.accountserver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
