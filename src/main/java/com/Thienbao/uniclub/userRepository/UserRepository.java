package com.Thienbao.uniclub.userRepository;

import com.Thienbao.uniclub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
