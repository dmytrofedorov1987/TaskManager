package dto;

public class WorkerDTO {
    private Long id;
    private String email;
    private String name;
    private String qualification;

    public WorkerDTO(Long id, String email, String name, String qualification) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.qualification = qualification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
