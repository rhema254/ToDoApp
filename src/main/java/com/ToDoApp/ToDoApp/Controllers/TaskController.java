package com.ToDoApp.ToDoApp.Controllers;

import com.ToDoApp.ToDoApp.Services.TaskService;
import com.ToDoApp.ToDoApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/Tasks")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public void createTask(@RequestBody Task task){

    }

    @GetMapping("/tasks")
    public void getTasks(){

    }

    @GetMapping("/task/{id}")
    public void getTask(){

    }

    @PutMapping("/update/{id}")
    public void updateTask(@PathVariable Long id){

    }

    @DeleteMapping("/delete/{id}")
    public void deleteTasking(@PathVariable Long id){

    }

    @PatchMapping("/archive/{id}")
    public void archiveTask(@PathVariable Long id){


    }
}
