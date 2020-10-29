package br.com.zup.casadocodigo.api.dtos.requests;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import br.com.zup.casadocodigo.api.annotations.ExisteIdAnnotation;
import br.com.zup.casadocodigo.domain.models.Compra;
import br.com.zup.casadocodigo.domain.models.Estado;
import br.com.zup.casadocodigo.domain.models.Pais;
import br.com.zup.casadocodigo.domain.models.Pedido;

public class CompraRequest {

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

	// Pais -> 1
	@NotNull
	@ExisteIdAnnotation(classe = Pais.class, atributo = "id")
	private Integer idPais;

	private Integer idEstado;

	@NotEmpty
	private String telefone;

	@NotEmpty
	private String cep;

	// PedidoRequest -> 2
	@Valid
	@NotNull
	private PedidoRequest pedido;

	public CompraRequest(@
			NotEmpty @Email String email, 
			@NotEmpty String nome, 
			@NotEmpty String sobrenome,
			@NotEmpty String documento, 
			@NotEmpty String endereco, 
			@NotEmpty String complemento,
			@NotEmpty String cidade, 
			@NotNull Integer idPais, 
			Integer idEstado, 
			@NotEmpty String telefone,
			@NotEmpty String cep, 
			@Valid PedidoRequest pedidoRequest) {

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
		this.pedido = pedidoRequest;

	}

	public boolean estaDocumentoValido() {

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		// branch oculta -> 5
		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);

	}

	public boolean estaEstadoValido(EntityManager entityManager) {

		// Uso de branch -> 3
		// Eu não sei em que ordem a bean validation vai invocar as annotations
		// Não é responsabilidade deste metodo validar se os ids são nulos, não é aqui
		// que faz isso
		if (idEstado.equals(null) || idPais.equals(null)) {
			return true;
		}

		// Estado -> 4
		Estado estado = entityManager.find(Estado.class, idEstado);
		Pais pais = entityManager.find(Pais.class, idPais);

		// branch oculta -> 6
		return pais.temEsse(estado);

	}

	// Falta validar se o estado ta valido, mas irei fazer isso quando criar a validation de estado pertence a pais
	public Compra toModel(EntityManager entityManager) {

		Pais pais = entityManager.find(Pais.class, this.idPais);
		Estado estado = entityManager.find(Estado.class, this.idEstado);

		// Pedido -> 7
		Pedido pedido = this.pedido.toModel(entityManager);
		
		// Compra > 8
		Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, estado,
				telefone, cep);

		compra.setPedido(pedido);
		
		return compra;
	}

	@Override
	public String toString() {
		return "CompraRequestDto [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", idPais=" + idPais + ", idEstado=" + idEstado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

}
