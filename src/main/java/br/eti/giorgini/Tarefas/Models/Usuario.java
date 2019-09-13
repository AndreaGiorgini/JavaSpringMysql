package br.eti.giorgini.Tarefas.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuarioId")
	private Long id;

	@Column(name = "usuarioNome", length = 255, nullable = false)
	@NotNull(message = "Preencha o campo de nome")
	@Length(max = 50, min = 5, message = "O nome deve conter entre 5 e 255 caracteres")
	private String nome;

	@Column(name = "usuarioLogin", length = 50, nullable = false)
	@NotNull(message = "Preencha o campo de login")
	@Length(max = 20, min = 5, message = "O login deve conter entre 5 e 20 caracteres")
	private String login;

	@Column(name = "usuarioSenha", length = 255, nullable = false)
	@NotNull(message = "Preencha o campo de senha")
	private String senha;

	@Column(name = "usuarioEmail", length = 255, nullable = false)
	@NotNull(message = "Preencha o campo de e-mail")
	@Length(max = 50, min = 10, message = "O e-mail deve conter entre 10 e 50 caracteres")
	private String email;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Tarefa> tarefa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
	}

}
