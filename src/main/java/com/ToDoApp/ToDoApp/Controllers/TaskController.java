package com.ToDoApp.ToDoApp.Controllers;

import com.ToDoApp.ToDoApp.Services.TaskService;
import com.ToDoApp.ToDoApp.Services.UserService;
import com.ToDoApp.ToDoApp.models.Task;
import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/Tasks")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestParam Long userId,
                                           @RequestPart("task") Task task,
                                           @RequestPart(value = "attachmentFile", required = false) MultipartFile attachmentFile) throws IOException {

        if (attachmentFile != null && !attachmentFile.isEmpty()) {
            String base64Attachment = Base64.getEncoder().encodeToString(attachmentFile.getBytes());
            task.setAttachment(base64Attachment);
        }

        Task createdTask = taskService.createTask(task, userId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable Long id){
        return taskService.getTask(id);
    }

    @GetMapping("/tasks/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask) {
        try {
            Task task = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

// ------ START HERE ON MONDAY!!!!------
//Changes the state to Archived.
    @PatchMapping("/archive/{id}")
    public ResponseEntity<?> archiveTask(@PathVariable Long id) {
        try {
            Task archivedTask = taskService.archiveTask(id);
            return ResponseEntity.ok(archivedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
