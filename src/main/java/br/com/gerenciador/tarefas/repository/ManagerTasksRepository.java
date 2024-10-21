package br.com.gerenciador.tarefas.repository;

import br.com.gerenciador.tarefas.entity.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerTasksRepository extends JpaRepository<Tasks, Long> {

    Page<Tasks> findByTitleContaining(String title, Pageable pageable);

    @NonNull
    Page<Tasks> findAll(@NonNull Pageable pageable);


}