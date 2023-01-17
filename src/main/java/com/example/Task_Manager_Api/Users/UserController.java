package com.example.Task_Manager_Api.Users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping(path = "login")
    public Users getUser(@RequestBody Users user){
        return userService.getUsers(user.getUsername(), user.getPassword());
    }

    @PostMapping(path = "register")
    public Users registerNewUser(@RequestBody Users user){
        userService.addNewUser(user);
        return userService.getUsers(user.getUsername(), user.getPassword());
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String password){
        userService.updateUser(id, name, password);
    }
}
