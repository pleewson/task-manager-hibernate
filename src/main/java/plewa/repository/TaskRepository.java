package plewa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plewa.entity.Task;
import plewa.entity.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByCompletedIsFalse();

    List<Task> findAllByUser(User user);

}
