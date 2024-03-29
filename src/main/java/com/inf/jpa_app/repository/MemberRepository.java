package com.inf.jpa_app.repository;

import com.inf.jpa_app.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    //@Autowired
    private final EntityManager em;

    public void save(Member member){

        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
         return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name",name).getResultList();
    }


}
