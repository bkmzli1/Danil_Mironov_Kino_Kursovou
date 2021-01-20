package ru.kino.demo.service.impl;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kino.demo.domian.User;
import ru.kino.demo.model.UserRegisterBindingModel;


public interface UserService extends UserDetailsService {
    void create(UserRegisterBindingModel userServiceModele);

    void edit(User userServiceModele);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

    UserRegisterBindingModel findByUsername(String username);

    User findById(String id);

}