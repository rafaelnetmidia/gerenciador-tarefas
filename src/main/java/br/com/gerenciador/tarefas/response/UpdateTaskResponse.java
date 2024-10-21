package br.com.gerenciador.tarefas.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateTaskResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String responsible;
    private Integer qtdHoursEstimated;
    private Integer qtdHoursWorked;



}
