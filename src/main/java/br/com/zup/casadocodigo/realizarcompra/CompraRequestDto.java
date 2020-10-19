package br.com.zup.casadocodigo.realizarcompra;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.casadocodigo.compartilhado.ExisteIdAnnotation;
import br.com.zup.casadocodigo.paisestado.Estado;
import br.com.zup.casadocodigo.paisestado.Pais;

public class CompraRequestDto {

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String sobrenome;

	@NotEmpty
	private String documento;

	@NotEmpty
	private String endereco;

	@NotEmpty
	private String complemento;

	@NotEmpty
	private String cidade;

	@NotNull
	@ExisteIdAnnotation(classe = Pais.class, atributo = "id")
	private Integer idPais;

	private Integer idEstado;

	@NotEmpty
	private String telefone;

	@NotEmpty
	private String cep;

	public CompraRequestDto(@NotEmpty @Email String email, @NotEmpty String nome, @NotEmpty String sobrenome,
			@NotEmpty String documento, @NotEmpty String endereco, @NotEmpty String complemento,
			@NotEmpty String cidade, @NotNull Integer idPais, Integer idEstado, @NotEmpty String telefone,
			@NotEmpty String cep) {

		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;

	}

	public Compra toModel(EntityManager entityManager) {

		Pais pais = entityManager.find(Pais.class, idPais);
		Estado estado = null;

		if (idEstado != null) {
			estado = entityManager.find(Estado.class, idEstado);
		}

		Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, estado,
				telefone, cep);

		return compra;
	}

	@Override
	public String toString() {
		return "CompraRequestDto [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", idPais=" + idPais + ", idEstado=" + idEstado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

}
