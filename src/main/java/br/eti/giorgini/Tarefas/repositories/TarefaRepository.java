package br.eti.giorgini.Tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eti.giorgini.Tarefas.Models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
