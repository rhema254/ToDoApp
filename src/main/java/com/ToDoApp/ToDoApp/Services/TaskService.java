package com.ToDoApp.ToDoApp.Services;

import com.ToDoApp.ToDoApp.Repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

        private final TaskRepository taskRepository;

        public TaskService(TaskRepository taskRepository){
            this.taskRepository = taskRepository;
        }
}
