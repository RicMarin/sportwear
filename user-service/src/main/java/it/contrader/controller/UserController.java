package it.contrader.controller;

import it.contrader.dto.UserDTO;
import it.contrader.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAllUsers")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    @GetMapping(value = "/getUser/{id}")
    public UserDTO getById(@PathVariable int id){
        return userService.getById(id);
    }

    @PostMapping(value = "/save")
    public void createUser(@RequestBody UserDTO userDTO){
        userService.insert(userDTO);
    }

    @PostMapping( value = "/update")
    public void updateUser(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
    }

    @DeleteMapping( value = "/delete/{id}")
    public void deleteUser(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping( value = "/existsById/{id}")
    public boolean existsById(@PathVariable int id){
        return userService.existsUserById(id);
    }

    @GetMapping(value = "/test")
    public String test(){
        return userService.test();
    }
}
