package model;

import dto.WorkerDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Worker {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String qualification;

    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "worker")
    private List<Task> tasks = new ArrayList<>();

    private Worker(String name, String qualification, String email) {
        this.name = name;
        this.qualification = qualification;
        this.email = email;
    }

    public static Worker of(String name, String qualification, String email) {
        return new Worker(name, qualification, email);
    }


    public static Worker fromWorkerDTO(WorkerDTO workerDTO) {
        Worker worker = Worker.of(workerDTO.getName(), workerDTO.getQualification(), workerDTO.getEmail());
        return worker;
    }

    public WorkerDTO toWorkerDTO() {
        return new WorkerDTO(id, email, name, qualification);
    }
public void addTaskToWorker(Task task){
        tasks.add(task);
        task.setWorker(this);
}

}
