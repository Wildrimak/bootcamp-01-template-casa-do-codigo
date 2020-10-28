package br.com.zup.casadocodigo.cadastroautor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(nullable = false)
	private String nome;

	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotEmpty
	@Size(max = 400)
	@Column(nullable = false)
	private String descricao;

	private LocalDateTime dataCadastro;

	public Autor() {
		this.dataCadastro = LocalDateTime.now();
	}

	public Autor(@NotEmpty String nome, @NotEmpty @Email String email, @NotEmpty @Size(max = 400) String descricao) {

		this();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

}
