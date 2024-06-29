package com.Thienbao.uniclub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "total")
    private double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private STATUS_ORDER status;

    @Column(name = "create_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetailList;

    @PrePersist
    protected void onCreate(){
        if (status == null){
            status = STATUS_ORDER.PENDING;
        }
    }
}
