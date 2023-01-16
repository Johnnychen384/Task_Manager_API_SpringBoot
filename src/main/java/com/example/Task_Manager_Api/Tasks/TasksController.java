package com.example.Task_Manager_Api.Tasks;

import com.example.Task_Manager_Api.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Task_Manager_Api.Users.Users;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/task")
public class TasksController {

    private final TasksService tasksService;
    private final UserRepository userRepository;

    @Autowired
    public TasksController(TasksService tasksService, UserRepository userRepository){
        this.userRepository = userRepository;
        this.tasksService = tasksService;
    }

    @GetMapping(path = "{userId}")
    public List<Tasks> getTasks(@PathVariable Long userId){
        Optional<Users> user = userRepository.findById(userId);
        if(user.isPresent()){
            return tasksService.getAllTasks(user.get());
        }else{
            throw new IllegalArgumentException("user not found");
        }
    }

    @PostMapping(path = "{userId}")
    public void addTask(@PathVariable("userId") Long userId, @RequestBody Tasks task){
        tasksService.addNewTask(userId, task);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTask(@PathVariable("id") Long id){
        tasksService.deleteTask(id);
    }

    @PutMapping(path = "{id}")
    public void updateTask(@PathVariable("id") Long id, @RequestParam(required = true) boolean done){
        tasksService.updateTask(id, done);
    }
}
