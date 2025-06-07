package plewa.controller;

import org.springframework.web.bind.annotation.*;
import plewa.entity.Task;
import plewa.service.TaskService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/get-all-tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }


    @GetMapping("/find-all-sort-by-date")
    public List<Task> findAllTasksSortByDate() {
        return taskService.findAllTasksSortByDate();
    }


    @GetMapping("/find-all-not-completed")
    public List<Task> findAllNotCompletedTasks() {
        return taskService.findAllNotCompletedTasks();
    }


    @GetMapping("/get-user-tasks/{userId}")
    public List<Task> getAllUserTasks(@PathVariable Long userId) {
        return taskService.getAllUserTasks(userId);
    }


    @PostMapping("/add/{description}/{dueDate}")
    public String addTask(@PathVariable String description, @PathVariable LocalDate dueDate) {
        taskService.addTask(description, dueDate);
        return "new task added";
    }


    @PostMapping("/add/{description}/{dueDate}/{userId}")
    public String addNewTaskAndAssignToUser(@PathVariable String description, @PathVariable LocalDate dueDate, @PathVariable Long userId) {
        taskService.addNewTaskAndAssignToUser(description, dueDate, userId);
        return "new task added and assigned to user " + userId;
    }


    @PutMapping("/change-status/{id}")
    public String changeTaskStatus(@PathVariable Long id) {
        taskService.changeTaskStatus(id);
        return "task " + id + " status has been changed";
    }


    @PutMapping("/add-user/{userId}/task/{taskId}")
    public String assignUserToTask(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.assignTaskToUser(userId, taskId);
        return "assigned task{" + taskId + "} to User{" + userId + "}";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "task " + id + " has been deleted";
    }


    @DeleteMapping("/delete-all-user-tasks/{userId}")
    public String deleteAllUserTasks(@PathVariable Long userId) {
        taskService.deleteAllUserTasks(userId);
        return "all user tasks has been deleted";
    }
}
