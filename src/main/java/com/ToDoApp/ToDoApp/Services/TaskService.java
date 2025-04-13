package com.ToDoApp.ToDoApp.Services;

import com.ToDoApp.ToDoApp.Enums.Status;
import com.ToDoApp.ToDoApp.Repositories.TaskRepository;
import com.ToDoApp.ToDoApp.Repositories.UserRepository;
import com.ToDoApp.ToDoApp.models.User;
import com.ToDoApp.ToDoApp.models.Task;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

        private final TaskRepository taskRepository;
        private final UserRepository userRepository;

        public TaskService(TaskRepository taskRepository, UserRepository userRepository){
            this.taskRepository = taskRepository;
            this.userRepository = userRepository;
        }

    public Task createTask(Task task, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        // Set the user for the task
        task.setUser(userOptional.get());

        // Set default values if not provided
        if (task.getStatus() == null) {
            task.setStatus(Status.TO_DO);
        }

        // Save and return the task
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(Long id){
            return taskRepository.findById(id);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }

        Task existingTask = taskOptional.get();

        // Update fields if provided
        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }

        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }

        if (updatedTask.getDueDate() != null) {
            existingTask.setDueDate(updatedTask.getDueDate());
        }

        if (updatedTask.getPriority() != null) {
            existingTask.setPriority(updatedTask.getPriority());
        }

        if (updatedTask.getStatus() != null) {
            existingTask.setStatus(updatedTask.getStatus());
        }

        if (updatedTask.getAttachmentUrl() != null) {
            existingTask.setAttachmentUrl(updatedTask.getAttachmentUrl());
        }

        if (updatedTask.getAttachment() != null) {
            existingTask.setAttachment(updatedTask.getAttachment());
        }

        // Update the updated_at timestamp
        existingTask.setUpdated_at(LocalDateTime.now());

        // Save and return the updated task
        return taskRepository.save(existingTask);
    }

    // Delete a task
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }

        taskRepository.deleteById(taskId);
    }

    // Archive a task
    public Task archiveTask(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }

        Task task = taskOptional.get();
        task.setStatus(Status.ARCHIVED);
        task.setUpdated_at(LocalDateTime.now());

        return taskRepository.save(task);
    }
}

