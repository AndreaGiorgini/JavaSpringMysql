package br.eti.giorgini.Tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eti.giorgini.Tarefas.Models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLogin(String login);

}
