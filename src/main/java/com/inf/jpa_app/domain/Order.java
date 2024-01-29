package com.inf.jpa_app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;


    //eager일대 한건을 조회할때는 조인 해서 memeber 모두 가져온다
    //but JPQL 사용할 경우  (select o from order o ->변환 select * from order 쿼리문을 날리고
    // 결과가 100건이라고 해보자 , 1건 마다
    //관련된 테이블 여기서는 member를 다시 조회한다  N+1 문제
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //orderItems 값을 넣고 order만 persis 해도 orderItems들을 하나하나 저장 안 해줘도 저장 된다
    //persist(order) 만 하면
    //persist(orderItems1), persist(orderItems2) 생략
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


 
    //일대 일 상황에서는 FK는 order 나 delivery 아무대나 가능하지만 
    //order 에서 delivery 찾을 일이 많기 때문에 order를 FK , 연관관계 주인으로 한다
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    //연관관계 메서드
    //핵심역활을 하는 쪽에 두는 것이 좋다
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
