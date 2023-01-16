package com.example.Task_Manager_Api.Tasks;

import javax.persistence.*;
import com.example.Task_Manager_Api.Users.Users;

@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;
    private String Task_Description;
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Tasks(){
    }

    public Tasks(Long id, String Task_Description, boolean done){
        this.id = id;
        this.Task_Description = Task_Description;
        this.done = done;

    }

    public Tasks(String Task_Description, boolean done){
        this.Task_Description = Task_Description;
        this.done = done;
    }


    public String getTask_Description(){
        return Task_Description;
    }

    public boolean getDone(){
        return done;
    }

    public Users getUser(){
        return user;
    }

    public void setTask_Description(String details){
        this.Task_Description = details;
    }

    public void setDone(boolean don){
        this.done = don;
    }

    public void setUser(Users use){
        this.user = use;
    }
}
