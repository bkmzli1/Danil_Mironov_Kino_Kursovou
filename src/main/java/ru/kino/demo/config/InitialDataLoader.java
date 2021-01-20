package ru.kino.demo.config;



import org.h2.engine.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.kino.demo.model.RoleServiceModel;
import ru.kino.demo.model.UserRegisterBindingModel;
import ru.kino.demo.service.impl.RoleService;
import ru.kino.demo.service.impl.UserService;


@Component
@CrossOrigin(origins = "http://localhost:4200")
public class InitialDataLoader implements ApplicationRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public InitialDataLoader(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public void run(ApplicationArguments args) {

        RoleServiceModel adminRole = this.roleService.findByAuthority("ADMIN");
        RoleServiceModel userRole = this.roleService.findByAuthority("USER");

        UserRegisterBindingModel userRoot = this.userService.findByUsername("root");
        UserRegisterBindingModel userExecutor = this.userService.findByUsername("Все исполнители");

        if (userRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("USER");
            this.roleService.addRole(roleServiceModel);
        }


        if (adminRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("ADMIN");
            this.roleService.addRole(roleServiceModel);
        }

        if (userRoot == null){
            UserRegisterBindingModel user = new UserRegisterBindingModel();
            user.setPassword("root");
            user.setFirstName("Админ");
            user.setLastName("Админ");
            user.setMiddleName("Админ");
            user.setEmail("-");
            user.setUsername("root");
            user.setAdmin(true);
            this.userService.create(user);
        }

    }
}