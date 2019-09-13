package br.eti.giorgini.Tarefas.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tarefa")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tarefaId")
	private Long id;

	@Column(name = "tarefaFechada", nullable = false)
	private Boolean fechada = false;

	@Column(name = "tarefaData", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;

	@Column(name = "tarefaDescricao", length = 255, nullable = false)
	@NotNull(message = "Preencha o campo de descrição da Tarefa")
	@Length(max = 100, min = 10, message = "A descrição deve conter entre 10 e 100 caracteres")
	private String descricao;

	@Column(name = "tarefaOs", length = 10, nullable = false)
	@NotNull(message = "Preencha o campo de Ordem de Serviço")
	@Length(max = 10, min = 5, message = "O numero da Ordem de Serviço deve conter entre 5 e 10 caracteres")
	private String os;

	@Column(name = "tarefaCliente", length = 255, nullable = false)
	@NotNull(message = "Preencha o campo de Cliente")
	@Length(max = 50, min = 5, message = "O cliente deve conter entre 5 e 50 caracteres")
	private String cliente;

	@Column(name = "tarefaTitulo", length = 255, nullable = false)
	@NotNull(message = "Preencha o campo de Título")
	@Length(max = 50, min = 5, message = "O título deve conter entre 5 e 50 caracteres")
	private String titulo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;
	
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	@JoinColumn(name = "usuarioId", updatable = false)
//	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFechada() {
		return fechada;
	}

	public void setFechada(Boolean fechada) {
		this.fechada = fechada;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
