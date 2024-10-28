package br.com.gerenciador.tarefas.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterTaskRequest {

    @NotBlank(message = "{register.task.request.title.mandatory}")
    private String title;
    @Length(max = 50, message = "{register.task.request.description.limit}")
    private String description;
    private Long creatorID;
    @NotNull(message = "{register.task.request.qtdHoursEstimated.mandatory}")
    private Integer qtdHoursEstimated;

}
