package br.com.gerenciador.tarefas.controller;

import br.com.gerenciador.tarefas.entity.Tasks;
import br.com.gerenciador.tarefas.request.RegisterTaskRequest;
import br.com.gerenciador.tarefas.request.UpdateTaskRequest;
import br.com.gerenciador.tarefas.response.RegisterTaskResponse;
import br.com.gerenciador.tarefas.response.TasksPagResponse;
import br.com.gerenciador.tarefas.response.TasksResponse;
import br.com.gerenciador.tarefas.response.UpdateTaskResponse;
import br.com.gerenciador.tarefas.service.ManagerTasksService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager-tasks")
@AllArgsConstructor
public class ManagerTasksController {

    private ManagerTasksService managerTasksService;

    @PostMapping
    public ResponseEntity<RegisterTaskResponse> save(@Valid @RequestBody RegisterTaskRequest request) {

        Tasks tasks = managerTasksService.saveTask(request);

        RegisterTaskResponse response = RegisterTaskResponse.builder()
                .id(tasks.getId())
                .title(tasks.getTitle())
                .description(tasks.getDescription())
                .creator(tasks.getCreator().getUserName())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TasksPagResponse> findByTasks(@RequestParam(required = false) String title,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "3") int size) {

        Page<Tasks> tasksPage = (title == null)
                ? managerTasksService.findByAllTasks(PageRequest.of(page, size))
                : managerTasksService.findByTasksToTitle(title, PageRequest.of(page, size));

        List<TasksResponse> tasks = tasksPage.getContent().stream()
                .map(task -> TasksResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .status(task.getStatus())
                        .responsible(task.getResponsible() != null ? task.getResponsible().getUserName() : "NÃO ATRIBUIDA")
                        .creator(task.getCreator().getUserName())
                        .qtdHoursEstimated(task.getQtdHoursEstimated())
                        .qtdHoursWorked(task.getQtdHoursWorked())
                        .registrationDate(task.getRegistrationDate())
                        .updateDate(task.getUpdateDate())
                        .build())
                .toList();

        TasksPagResponse response = TasksPagResponse.builder()
                .currentPage(tasksPage.getNumber())
                .totalItems(tasksPage.getTotalElements())
                .totalPages(tasksPage.getTotalPages())
                .tasks(tasks)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateTaskResponse> update(@PathVariable Long id, @RequestBody UpdateTaskRequest request) {

        Tasks tasksUpdate = managerTasksService.updateTask(request, id);

        UpdateTaskResponse response = UpdateTaskResponse.builder()
                .id(tasksUpdate.getId())
                .title(tasksUpdate.getTitle())
                .description(tasksUpdate.getDescription())
                .status(tasksUpdate.getStatus().toString())
                .responsible(tasksUpdate.getResponsible().getUserName())
                .qtdHoursEstimated(tasksUpdate.getQtdHoursEstimated())
                .qtdHoursWorked(tasksUpdate.getQtdHoursWorked())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        managerTasksService.deleteTask(id);
    }

}
