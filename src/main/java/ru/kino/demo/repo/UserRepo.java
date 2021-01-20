package ru.kino.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kino.demo.domian.Roles;
import ru.kino.demo.domian.User;

import java.util.List;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, String> {

    Set<User> findAllBy();

    User findOneByUsername(String username);

    User findByEmail(String email);

    User findOneById(String id);



}
