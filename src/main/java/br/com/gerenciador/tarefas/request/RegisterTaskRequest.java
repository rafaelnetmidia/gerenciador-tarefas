package br.com.gerenciador.tarefas.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTaskRequest {

    private String title;
    private String description;
    private Long creatorID;
    private Integer qtdHoursEstimated;

}
