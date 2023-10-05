package repositoryJPA;

import dto.TaskToNotifyDTO;
import model.Condition;
import model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskByProject(String nameProject, Pageable pageable);


    List<Task> findTaskByCondition(Condition condition, Pageable pageable);

    @Query("SELECT NEW com.example.taskmanager.dto.TaskToNotifyDTO(w.email, t.dateStart, t.text)" + //TODO
            "FROM Worker w, Task t WHERE t.dateStart >= :from AND t.dateStart < :to")
    List<TaskToNotifyDTO> findTaskToNotify(@Param("to") Date to, @Param("from") Date from);

}
