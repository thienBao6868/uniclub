package com.Thienbao.uniclub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "role")
    private List<User> users;
}
