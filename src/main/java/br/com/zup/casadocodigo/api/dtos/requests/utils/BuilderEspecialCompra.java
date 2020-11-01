package br.com.zup.casadocodigo.api.dtos.requests.utils;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.api.dtos.requests.PedidoRequest;
import br.com.zup.casadocodigo.domain.models.Compra;
import br.com.zup.casadocodigo.domain.models.CupomRepository;
import br.com.zup.casadocodigo.domain.models.Estado;
import br.com.zup.casadocodigo.domain.models.Pais;

public class BuilderEspecialCompra { // cdd: 7

	private EntityManager entityManager;
	private CupomRepository cupomRepository; // 1
	private String email;
	private String nome;
	private String sobrenome;
	private String documento;
	private String endereco;
	private String complemento;
	private String cidade;
	private Integer idPais;
	private Integer idEstado;
	private String telefone;
	private String cep;
	private String codigoCupom;
	private PedidoRequest pedidoRequest; // 1

	
	
	public BuilderEspecialCompra(EntityManager entityManager, CupomRepository cupomRepository) {
		this.entityManager = entityManager;
		this.cupomRepository = cupomRepository;
	}

	public BuilderEspecialCompra withEmail(String email) {
		this.email = email;
		return this;
	}

	public BuilderEspecialCompra withNome(String nome) {
		this.nome = nome;
		return this;
	}

	public BuilderEspecialCompra withSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
		return this;
	}

	public BuilderEspecialCompra withDocumento(String documento) {
		this.documento = documento;
		return this;
	}

	public BuilderEspecialCompra withEndereco(String endereco) {
		this.endereco = endereco;
		return this;
	}

	
	public BuilderEspecialCompra withComplemento(String complemento) {
		this.complemento = complemento;
		return this;
	}

	public BuilderEspecialCompra withCidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public BuilderEspecialCompra withIdPais(Integer idPais) {
		this.idPais = idPais;
		return this;
	}

	public BuilderEspecialCompra withIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
		return this;
	}

	public BuilderEspecialCompra withTelefone(String telefone) {
		this.telefone = telefone;
		return this;
	}

	public BuilderEspecialCompra withCep(String cep) {
		this.cep = cep;
		return this;
	}

	public BuilderEspecialCompra withCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
		return this;
	}

	public BuilderEspecialCompra withPedidoRequest(PedidoRequest pedidoRequest) {
		this.pedidoRequest = pedidoRequest;
		return this;
	}

	public Compra build() { // 1

		Pais pais = getPais(); // 1
		Estado estado = getEstado(); // 1

		Assert.state(this.email != null, "Não foi informado o email");
		Assert.state(this.nome != null, "Não foi informado o nome");
		Assert.state(this.sobrenome != null, "Não foi informado o sobrenome");
		Assert.state(this.documento != null, "Não foi informado o documento");
		Assert.state(this.endereco != null, "Não foi informado o endereco");
		Assert.state(this.complemento != null, "Não foi informado o complemento");
		Assert.state(this.cidade != null, "Não foi informado o cidade");
		Assert.state(this.idPais != null, "Não foi informado o idPais");
		Assert.state(this.telefone != null, "Não foi informado o telefone");
		Assert.state(this.cep != null, "Não foi informado o cep");
		
		
		Compra comprador = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, estado, telefone, cep);

		// 1
		Compra compraCompleta = new BuilderFragmentadoCompraDetalhesPedidoCupom(entityManager, cupomRepository, comprador)
				.withCodigoCupom(codigoCupom)
				.withPedidoRequest(pedidoRequest)
				.build();
		

		return compraCompleta;
	}

	private Estado getEstado() {

		if (this.idEstado == null) { // 1
			return null;
		}
		
		@NotNull Estado estado = entityManager.find(Estado.class, this.idEstado);
		Assert.state(estado != null, "Não existe Estado com id " + this.idEstado + " no banco de dados");
		
		return estado;
		
	}

	private Pais getPais() {
		
		@NotNull Pais pais = entityManager.find(Pais.class, this.idPais);
		Assert.state(pais != null, "Não existe Pais com id " + this.idPais + " no banco de dados");
		
		return pais;
	}

	
}
