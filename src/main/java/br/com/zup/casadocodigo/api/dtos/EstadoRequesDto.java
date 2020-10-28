package br.com.zup.casadocodigo.api.dtos;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.casadocodigo.api.annotations.ExisteIdAnnotation;
import br.com.zup.casadocodigo.api.annotations.ValorUnicoAnnotation;
import br.com.zup.casadocodigo.domain.models.Estado;
import br.com.zup.casadocodigo.domain.models.Pais;

public class EstadoRequesDto {

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "nome", classe = Estado.class)
	private String nome;

	@NotNull
	@ExisteIdAnnotation(atributo = "id", classe = Pais.class)
	private Integer idPais;

	public EstadoRequesDto(@NotEmpty String nome, @NotNull Integer idPais) {
		this.nome = nome;
		this.idPais = idPais;
	}

	public Estado toModel(EntityManager entityManager) {

		Estado estado = new Estado(this.nome, entityManager.find(Pais.class, this.idPais));

		return estado;
	}

}
