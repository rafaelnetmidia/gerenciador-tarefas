package br.com.gerenciador.tarefas.entity;

import br.com.gerenciador.tarefas.enums.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Data
@Builder
public class Tasks implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private Users responsible;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Users creator;

    @Column(nullable = false)
    private Integer qtdHoursEstimated;

    @Column
    private Integer qtdHoursWorked;

    @Column
    @CreationTimestamp
    private LocalDateTime registrationDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public Tasks(Long id, String title, String description, TaskStatusEnum status, Users responsible, Users creator, Integer qtdHoursEstimated, Integer qtdHoursWorked, LocalDateTime registrationDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.responsible = responsible;
        this.creator = creator;
        this.qtdHoursEstimated = qtdHoursEstimated;
        this.qtdHoursWorked = qtdHoursWorked;
        this.registrationDate = registrationDate;
        this.updateDate = updateDate;
    }

    public Tasks() {

    }
}
