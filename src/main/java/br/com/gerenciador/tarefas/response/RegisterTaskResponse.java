package br.com.gerenciador.tarefas.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterTaskResponse {

    private Long id;
    private String title;
    private String description;
    private String creator;


}
