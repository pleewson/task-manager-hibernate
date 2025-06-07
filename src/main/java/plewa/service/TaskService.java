package plewa.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import plewa.entity.Task;
import plewa.entity.User;
import plewa.repository.TaskRepository;
import plewa.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void addTask(String description, LocalDate dueDate) {
        Task task = new Task(description, dueDate);
        taskRepository.save(task);
    }

    public void changeTaskStatus(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));

        if (!task.isCompleted()) {
            task.setCompleted(true);
        } else {
            task.setCompleted(false);
        }

        taskRepository.save(task);
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }


    public List<Task> findAllNotCompletedTasks() {
        return taskRepository.findAllByCompletedIsFalse();
    }


    public List<Task> findAllTasksSortByDate() {
        return taskRepository.findAll(Sort.by("dueDate").ascending());
    }


    public void assignTaskToUser(Long userId, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        task.setUser(user);

        taskRepository.save(task);
    }


    public void addNewTaskAndAssignToUser(String description, LocalDate dueDate, Long userId) {
        Task task = new Task(description, dueDate);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        task.setUser(user);

        taskRepository.save(task);
    }


    public List<Task> getAllUserTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findAllByUser(user);
    }


    public void deleteAllUserTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Task> taskList = taskRepository.findAllByUser(user);

        for (Task task : taskList) {
            taskRepository.delete(task);
        }

    }
}
