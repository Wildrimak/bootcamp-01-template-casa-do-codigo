package br.com.zup.casadocodigo.estado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.compartilhado.ValorUnicoAnnotation;
import br.com.zup.casadocodigo.pais.Pais;

public class EstadoRequesDto {

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "nome", classe = Estado.class)
	private String nome;

	@NotNull
	private Integer idPais;

	public EstadoRequesDto(@NotEmpty String nome, @NotNull Integer idPais) {
		this.nome = nome;
		this.idPais = idPais;
	}

	public Estado toModel(EntityManager entityManager) {

		@NotNull
		Pais pais = entityManager.find(Pais.class, this.idPais);

		Assert.notNull(pais, "Não existe esse pais cadastrado cujo id é " + this.idPais);

		Estado estado = new Estado(this.nome, pais);

		return estado;
	}

}
