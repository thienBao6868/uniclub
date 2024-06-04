package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.model.User;
import com.Thienbao.uniclub.payload.request.SignupRequest;
import com.Thienbao.uniclub.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean insertUser(SignupRequest signupRequest){
        boolean isSuccess = false;
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
//        user.setIdRole(2);
        try{
            userRepository.save(user);
            isSuccess = true;
        }catch (Exception ex){
            System.out.println("Error Insert User");
        }


        return isSuccess;
    }

    public User findByEmail (String email){
        return userRepository.findByEmail(email);
    }
}
