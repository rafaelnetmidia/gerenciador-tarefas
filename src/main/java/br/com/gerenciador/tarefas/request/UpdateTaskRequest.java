package br.com.gerenciador.tarefas.request;

import br.com.gerenciador.tarefas.enums.TaskStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskRequest {

    private String title;
    private String description;
    private TaskStatusEnum status;
    private Long responsible;
    private Integer qtdHoursEstimated;
    private Integer qtdHoursWorked;

}
