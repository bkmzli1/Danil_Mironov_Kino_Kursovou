package ru.kino.demo.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.kino.demo.domian.User;
import ru.kino.demo.model.UserRegisterBindingModel;
import ru.kino.demo.repo.ImgRepo;
import ru.kino.demo.repo.UserRepo;
import ru.kino.demo.service.impl.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;


    private final UserRepo userRepository;
    private final ImgRepo imgRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, UserRepo userRepository, ImgRepo imgRepository,
                          ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.imgRepository = imgRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping(value = "/reg")
    @ResponseBody
    public Map<String, String> registerConfirm(@RequestBody() @Valid UserRegisterBindingModel userRegisterBindingModel,
                                               BindingResult bindingResult,
                                               HttpServletRequest request) {

        Map<String, String> strings = new HashMap<>();
        if (bindingResult.hasErrors()) {
            String errorS = "";
            int size = 0;
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorS += error.getDefaultMessage();
                size++;
                if (size < bindingResult.getAllErrors().size()) {
                    errorS += ",";
                } else {
                    errorS += ".";
                }
            }
            strings.put("error", errorS);
            return strings;
        } else {
            UserRegisterBindingModel userServiceModel = this.modelMapper
                    .map(userRegisterBindingModel, UserRegisterBindingModel.class);
            this.userService.create(userServiceModel);
            strings.put("error", null);
        }
        return strings;
    }

    @GetMapping
    @RequestMapping("/user")
    public User user(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return user;
        } catch (NullPointerException npe) {

        }
        return null;

    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User userID(@PathVariable String id) {
        User user = userRepository.findOneById(id);
        return user;
    }

    @PostMapping("/save")
    @ResponseBody
    public User userSave(@RequestBody() UserRegisterBindingModel userRegisterBindingModel,
                         Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out
                .println(bCryptPasswordEncoder.matches(userRegisterBindingModel.getOldPassword(), user.getPassword()));
        ;
        if (bCryptPasswordEncoder.matches(userRegisterBindingModel.getOldPassword(), user.getPassword())) {
            user.setFirstName(userRegisterBindingModel.getFirstName());
            user.setMiddleName(userRegisterBindingModel.getMiddleName());
            user.setLastName(userRegisterBindingModel.getLastName());
            user.setPassword(bCryptPasswordEncoder.encode(userRegisterBindingModel.getPassword()));
            userRepository.save(user);
        }
        return user;
    }




}