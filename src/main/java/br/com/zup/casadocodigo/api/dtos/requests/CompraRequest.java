package br.com.zup.casadocodigo.api.dtos.requests;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.casadocodigo.api.annotations.ExisteIdAnnotation;
import br.com.zup.casadocodigo.api.dtos.requests.utils.BuilderEspecialCompra;
import br.com.zup.casadocodigo.api.dtos.requests.utils.ValidadoresCompra;
import br.com.zup.casadocodigo.domain.models.Compra;
import br.com.zup.casadocodigo.domain.models.Cupom;
import br.com.zup.casadocodigo.domain.models.CupomRepository;
import br.com.zup.casadocodigo.domain.models.Estado;
import br.com.zup.casadocodigo.domain.models.Pais;

public class CompraRequest { // cdd: 5? -> Se não contar as annotations

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

	@ExisteIdAnnotation(classe = Estado.class, atributo = "id")
	private Integer idEstado;

	@NotEmpty
	private String telefone;

	@NotEmpty
	private String cep;

	// PedidoRequest -> 1
	@Valid
	@NotNull
	private PedidoRequest pedido;

	@ExisteIdAnnotation(classe = Cupom.class, atributo = "codigo")
	private String codigoCupom;

	public CompraRequest(@NotEmpty @Email String email, @NotEmpty String nome, @NotEmpty String sobrenome,
			@NotEmpty String documento, @NotEmpty String endereco, @NotEmpty String complemento,
			@NotEmpty String cidade, @NotNull Integer idPais, Integer idEstado, @NotEmpty String telefone,
			@NotEmpty String cep, @Valid PedidoRequest pedido, String codigoCupom) {

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
		this.pedido = pedido;
		this.codigoCupom = codigoCupom;
	}

	public String getDocumento() {
		return documento;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public Optional<String> getCodigoCupom() {
		return Optional.ofNullable(codigoCupom);
	}

	// Isso daqui pq o cupom foi erroneamente especificado em compra e não em pedido
	// que é o lugar certo
	public BigDecimal getTotal() {
		return pedido.getTotal();
	}

	// ValidadoresCompra -> 1
	public boolean documentoValido() {
		return ValidadoresCompra.validaDocumento(documento);
	}

	public boolean temEstado() {
		return ValidadoresCompra.validaTemEstado(idEstado);
	}

	public boolean valorEnviadoValido(EntityManager entityManager, CupomRepository cupomRepository) {
		return ValidadoresCompra.validaValorPedido(entityManager, pedido, codigoCupom, cupomRepository);
	}

	// CupomRepository -> 1
	public Compra toModel(EntityManager entityManager, CupomRepository cupomRepository) {

		// Compra -> 1, Builder -> 1
		Compra compra = new BuilderEspecialCompra(entityManager, cupomRepository)
				.withEmail(email)
				.withNome(nome)
				.withSobrenome(sobrenome)
				.withDocumento(documento)
				.withEndereco(endereco)
				.withComplemento(complemento)
				.withCidade(cidade)
				.withIdPais(idPais)
				.withIdEstado(idEstado)
				.withTelefone(telefone)
				.withCep(cep)
				.withPedidoRequest(pedido)
				.withCodigoCupom(codigoCupom)
				.build();

		return compra;
	}

	@Override
	public String toString() {
		return "CompraRequestDto [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", idPais=" + idPais + ", idEstado=" + idEstado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

}
