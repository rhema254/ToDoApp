package com.ToDoApp.ToDoApp.Repositories;

import com.ToDoApp.ToDoApp.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{


}
