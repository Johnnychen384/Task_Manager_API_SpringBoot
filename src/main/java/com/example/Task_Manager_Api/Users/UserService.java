package com.example.Task_Manager_Api.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users getUsers(String name, String password){

        Optional<Users> userByUsername = userRepository.findByUsername(name);

        if(userByUsername.isPresent()){
            Users user = userByUsername.get();
            String storedPassword = user.getPassword();
            if (storedPassword.equals(password)) {
                //password matches
                return user;
            }else{
                //password does not match
                throw new IllegalArgumentException("Invalid password");
            }
        }else{
            throw new IllegalArgumentException("user not found");
        }

    }

    public Users addNewUser(Users user){
        Optional<Users> userByUsername = userRepository.findByUsername((user.getUsername()));
        if(userByUsername.isPresent()){
            throw new IllegalStateException("Username taken.");
        }
        userRepository.save(user);
        Optional<Users> newUser = userRepository.findByUsername(user.getUsername());
        Users user1 = userByUsername.get();
        return user1;
    }

    public void deleteUser(Long id){
        boolean user = userRepository.existsById(id);
        if(!user){
            throw new IllegalStateException("This user doesn't exist");
        }

        userRepository.deleteById(id);
    }
    @Transactional
    public void updateUser(Long id, String name, String password){
        Users user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User does not exists."));

        if(name != null){
            Optional<Users> usersOptional = userRepository.findByUsername(name);

            if(usersOptional.isPresent()){
                throw new IllegalStateException("Already Taken.");
            }
            user.setUsername(name);
        }

        if(password != null){
            user.setPassword(password);
        }
    }

}
