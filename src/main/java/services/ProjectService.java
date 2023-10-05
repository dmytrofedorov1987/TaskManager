package services;

import dto.ProjectDTO;
import model.Condition;
import model.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositoryJPA.ProjectRepository;

import java.awt.print.Pageable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService implements ProjectServiceInterface {
    private final ProjectRepository projectRepository;


    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    @Override
    public void addProject(ProjectDTO projectDTO) {
        if (projectRepository.existsByName(projectDTO.getName()))
            return;

        Project project = Project.fromProjectDTO(projectDTO);
        projectRepository.save(project);

    }

    @Transactional
    @Override
    public void deleteProject(List<Long> idList) {
        idList.forEach((a) -> projectRepository.deleteById(a));
    }

    @Transactional
    @Override
    public void updateProjectName(ProjectDTO projectDTO) {
        var projectOptional = projectRepository.findById(projectDTO.getId());
        Project project = new Project();
        if (projectOptional.isPresent()) {
            project = projectOptional.get();
        }
        project.setName(projectDTO.getName());
        projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProjectDTO> getProjects(Condition condition, Pageable pageable) {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectDTO> projectDTOList = projectList.stream().
                filter(a -> a.getCondition().equals(condition)).
                map((a) -> a.toProjectDTO()).
                sorted(Comparator.comparing(ProjectDTO::getId).reversed()).
                collect(Collectors.toList());
        return projectDTOList;
    }

    public ProjectDTO getProject(Long id) {
        var projectOptional = projectRepository.findById(id);
        Project project = new Project();
        if (projectOptional.isPresent()) {
            project = projectOptional.get();

        }
        return project.toProjectDTO();
    }
}
