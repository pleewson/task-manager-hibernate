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


    @PostMapping("/add/{description}/{dueDate}")
    public String addTask(@PathVariable String description, @PathVariable LocalDate dueDate) {
        taskService.addTask(description, dueDate);
        return "new task added";
    }


    @PutMapping("/change-status/{id}")
    public String changeTaskStatus(@PathVariable Long id) {
        taskService.changeTaskStatus(id);
        return "task " + id + " status has been changed";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "task " + id + " has been deleted";
    }
}
