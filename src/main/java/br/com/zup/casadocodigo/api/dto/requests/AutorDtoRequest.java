package br.com.zup.casadocodigo.api.dto.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.zup.casadocodigo.domain.models.Autor;

public class AutorDtoRequest {

	@NotEmpty
	private String nome;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Size(max = 400)
	private String descricao;

	public AutorDtoRequest(
			@NotEmpty String nome, 
			@NotEmpty @Email String email, 
			@NotEmpty @Size(max = 400) String descricao) {

		this.nome = nome;
		this.email = email;
		this.descricao = descricao;

	}

	public Autor toModel() {
		return new Autor(nome, email, descricao);
	}

}
