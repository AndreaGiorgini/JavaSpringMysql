package br.eti.giorgini.Tarefas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.eti.giorgini.Tarefas.Models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	@Query("Select t from Tarefa as t where t.usuario.id = :usuarioId")
	List<Tarefa> carregarTarefasPorUsuario(@Param("usuarioId") Long usuarioId);
}
