package br.com.gerenciador.tarefas.response;

import br.com.gerenciador.tarefas.enums.TaskStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TasksResponse {

    private Long id;
    private String title;
    private String description;
    private TaskStatusEnum status;
    private String responsible;
    private String creator;
    private Integer qtdHoursEstimated;
    private Integer qtdHoursWorked;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;

}
