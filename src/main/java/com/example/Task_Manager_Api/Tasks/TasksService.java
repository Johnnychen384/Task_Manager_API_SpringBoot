package com.example.Task_Manager_Api.Tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Task_Manager_Api.Users.Users;
import com.example.Task_Manager_Api.Users.UserRepository;

import javax.transaction.Transactional;

@Service
public class TasksService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TasksService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Tasks> getAllTasks(Users user){
        return taskRepository.findAllByUser(user);
    }

    public void addNewTask(Long userId, Tasks task){
        Users user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User does not exists."));
        task.setUser(user);
        taskRepository.save(task);
    }

    public void deleteTask(Long id){
        boolean task = taskRepository.existsById(id);
        if(!task){
            throw new IllegalStateException("Task does not exists.");
        }

        taskRepository.deleteById(id);
    }

    @Transactional
    public void updateTask(Long id, boolean done){
        Tasks task = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Task does not exists."));
        task.setDone(done);
    }
}
