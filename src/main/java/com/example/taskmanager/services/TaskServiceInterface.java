package com.example.taskmanager.services;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.TaskToNotifyDTO;
import com.example.taskmanager.model.Condition;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskServiceInterface {
    void addTask(TaskDTO taskDTO, Long idProject);

    void deleteTask(Long id, Long idProject);

    void updateTask(TaskDTO taskDTO);

    TaskDTO getTask(Long id);

    List<TaskToNotifyDTO> getTasksToNotify(LocalDateTime now);

    List<TaskDTO> getProjectTasks(Long idProject, Condition taskCondition, PageRequest pageable);

    Long countTask(Condition taskCondition, Long idProject);
}
