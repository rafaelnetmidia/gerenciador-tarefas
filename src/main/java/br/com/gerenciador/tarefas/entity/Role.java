package br.com.gerenciador.tarefas.entity;


import br.com.gerenciador.tarefas.permissions.PermissionEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Data
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PermissionEnum name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<Users> users;



}
