package com.ToDoApp.ToDoApp.Repositories;

import com.ToDoApp.ToDoApp.Enums.Status;
import com.ToDoApp.ToDoApp.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    // Find all tasks for a specific user
    List<Task> findByUserId(Long userId);

    // Find tasks by user ID and status
    List<Task> findByUserIdAndStatus(Long userId, Status status);

    // Find tasks by user ID and due date
    List<Task> findByUserIdAndDueDate(Long userId, LocalDate dueDate);

    // Find overdue tasks that are not completed
    List<Task> findByUserIdAndDueDateBeforeAndStatusNot(
            Long userId,
            LocalDate dueDate,
            Status status
    );

    // Find tasks sorted by priority
    List<Task> findByUserIdOrderByPriorityDesc(Long userId);

    // Find tasks for a specific user sorted by due date
    List<Task> findByUserIdOrderByDueDateAsc(Long userId);
}
