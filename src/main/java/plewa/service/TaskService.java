package plewa.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import plewa.entity.Task;
import plewa.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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

}
