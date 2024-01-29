package com.inf.jpa_app.service;

import com.inf.jpa_app.domain.Member;
import com.inf.jpa_app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private  final MemberRepository memberRepository; //final 하는이유


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long login(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId(); //save , persist 하면 영속성 컨텍스트에 pk 생성, 즉 id값이 보장된다.
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findMemberById(Long memberId){
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember(Member member) {
        //exception
         List<Member> members = memberRepository.findByName(member.getName());
        if(members.size() > 0){//동시성 문제, 유니크로
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

}
