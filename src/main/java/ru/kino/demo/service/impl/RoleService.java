package ru.kino.demo.service.impl;


import ru.kino.demo.domian.Roles;
import ru.kino.demo.model.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByAuthority(String authority);

    void addRole(RoleServiceModel roleServiceModel);
}
