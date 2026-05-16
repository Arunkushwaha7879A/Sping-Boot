package com.first.Services;

import com.first.repositries.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean dologin(){
        //login logic
        loginRepository.getuser();
        return true;
    }

}
