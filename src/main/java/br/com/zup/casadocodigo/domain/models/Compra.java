package br.com.zup.casadocodigo.domain.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Email
	@NotEmpty
	@Column(nullable = false)
	private String email;

	@NotEmpty
	@Column(nullable = false)
	private String nome;

	@NotEmpty
	@Column(nullable = false)
	private String sobrenome;

	@NotEmpty
	@Column(nullable = false)
	private String documento;

	@NotEmpty
	@Column(nullable = false)
	private String endereco;

	@NotEmpty
	@Column(nullable = false)
	private String complemento;

	@Column(nullable = false)
	@NotEmpty
	private String cidade;

	@NotNull
	@ManyToOne
	private Pais pais;

	@ManyToOne
	private Estado estado;

	@NotEmpty
	private String telefone;

	@NotEmpty
	private String cep;
	
	@NotNull
	@OneToOne(mappedBy = "compra",cascade = CascadeType.PERSIST)
	private Pedido pedido;

	@Deprecated
	public Compra() {
	}

	public Compra(
			@NotEmpty @Email String email, 
			@NotEmpty String nome, 
			@NotEmpty String sobrenome,
			@NotEmpty String documento, 
			@NotEmpty String endereco, 
			@NotEmpty String complemento,
			@NotEmpty String cidade, 
			@NotNull Pais pais, 
			Estado estado, 
			@NotEmpty String telefone, 
			@NotEmpty String cep,
			@NotNull Pedido pedido) {

		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.pais = pais;
		this.estado = estado;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", pais=" + pais.getNome() + ", estado=" + estado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

}
