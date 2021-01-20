package ru.kino.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kino.demo.domian.Roles;


public interface RoleRepo extends JpaRepository<Roles, String> {
    Roles findByAuthority(String authority);
}
