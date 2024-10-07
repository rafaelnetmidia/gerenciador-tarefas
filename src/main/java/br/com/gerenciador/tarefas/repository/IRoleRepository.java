package br.com.gerenciador.tarefas.repository;

import br.com.gerenciador.tarefas.entity.Role;
import br.com.gerenciador.tarefas.permissions.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByName(PermissionEnum name);

}
