package com.inf.jpa_app.service;

import com.inf.jpa_app.domain.Address;
import com.inf.jpa_app.domain.Member;
import com.inf.jpa_app.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void login() {
        Member member = new Member();
        member.setName("kim");
        Address address =new Address("testCity","street1","code1");
        member.setAddress(address);
        Long login = memberService.login(member);

      //  Assertions.assertThat(member.equals(memberRepository.findOne(login)));

    }

    @Test
    void login2() {
    }

    @Test
    void findMembers() {
    }

    @Test
    void findMemberById() {
    }
}