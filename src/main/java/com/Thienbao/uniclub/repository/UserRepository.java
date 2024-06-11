package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
