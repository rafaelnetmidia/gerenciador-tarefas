package br.com.gerenciador.tarefas.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TasksPagResponse {

    private Integer currentPage;
    private Long totalItems;
    private Integer totalPages;
    private List<TasksResponse> tasks;
}
