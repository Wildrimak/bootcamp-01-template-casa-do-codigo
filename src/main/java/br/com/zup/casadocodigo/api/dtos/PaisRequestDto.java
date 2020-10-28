package br.com.zup.casadocodigo.api.dtos;

import javax.validation.constraints.NotEmpty;

import br.com.zup.casadocodigo.api.annotations.ValorUnicoAnnotation;
import br.com.zup.casadocodigo.domain.models.Pais;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisRequestDto {

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "nome", classe = Pais.class)
	private String nome;
	
}
