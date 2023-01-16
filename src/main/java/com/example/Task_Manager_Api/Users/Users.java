package com.example.Task_Manager_Api.Users;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String password;

    public Users(){
    }

    public Users(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;

    }

    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }


    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String name){
        this.username = name;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

}
