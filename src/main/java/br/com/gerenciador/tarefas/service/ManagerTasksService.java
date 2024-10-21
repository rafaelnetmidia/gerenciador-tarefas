package br.com.gerenciador.tarefas.service;

import br.com.gerenciador.tarefas.entity.Tasks;
import br.com.gerenciador.tarefas.enums.TaskStatusEnum;
import br.com.gerenciador.tarefas.repository.ManagerTasksRepository;
import br.com.gerenciador.tarefas.request.RegisterTaskRequest;
import br.com.gerenciador.tarefas.request.UpdateTaskRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor
@Transactional
public class ManagerTasksService {

    private ManagerTasksRepository managerTasksRepository;
    private UserService userService;

    public Tasks saveTask(RegisterTaskRequest request) {

        Tasks tasks = Tasks.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatusEnum.CRIADA)
                .qtdHoursEstimated(request.getQtdHoursEstimated())
                .creator(userService.findById(request.getCreatorID()))
                .build();

        return managerTasksRepository.save(tasks);
    }

    public Page<Tasks> findByTasksToTitle(String  title, Pageable pageable) {
        return managerTasksRepository.findByTitleContaining(title, pageable);
    }

    public Page<Tasks> findByAllTasks(Pageable pageable) {
        return this.managerTasksRepository.findAll(pageable);
    }

    public void deleteTask(Long taskId) {
        this.managerTasksRepository.deleteById(taskId);
    }

    public Tasks updateTask(UpdateTaskRequest request, Long taskId) {

        Tasks task = this.managerTasksRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setResponsible(userService.findById(request.getResponsible()));
        task.setQtdHoursEstimated(request.getQtdHoursEstimated());
        task.setQtdHoursWorked(request.getQtdHoursWorked());

        return this.managerTasksRepository.save(task);
    }

}
