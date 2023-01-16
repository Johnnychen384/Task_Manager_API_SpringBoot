package com.example.Task_Manager_Api.Tasks;


import com.example.Task_Manager_Api.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findAllByUser(Users user);

}
