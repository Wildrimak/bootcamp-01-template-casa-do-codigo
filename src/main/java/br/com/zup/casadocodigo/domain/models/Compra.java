package br.com.zup.casadocodigo.domain.models;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "compra", cascade = CascadeType.PERSIST)
	private Pedido pedido;

	@Embedded
	private CupomAplicado cupomAplicado;

	public Compra() {
	}

	public Compra(@NotEmpty @Email String email, @NotEmpty String nome, @NotEmpty String sobrenome,
			@NotEmpty String documento, @NotEmpty String endereco, @NotEmpty String complemento,
			@NotEmpty String cidade, @NotNull Pais pais, Estado estado, @NotEmpty String telefone,
			@NotEmpty String cep) {

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
	}

	private Compra(@NotEmpty @Email String email, @NotEmpty String nome, @NotEmpty String sobrenome,
			@NotEmpty String documento, @NotEmpty String endereco, @NotEmpty String complemento,
			@NotEmpty String cidade, @NotNull Pais pais, Estado estado, @NotEmpty String telefone, @NotEmpty String cep,
			CupomAplicado cupomAplicado, @NotNull Pedido pedido) {

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
		this.cupomAplicado = cupomAplicado;
		this.pedido = pedido;

	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public Pais getPais() {
		return pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Optional<CupomAplicado> getOptionalCupomAplicado() {

		if (cupomAplicado == null) {
			return Optional.empty();
		}

		return Optional.of(cupomAplicado);
	}

	public void setCupomAplicado(CupomAplicado cupomAplicado) {
		this.cupomAplicado = cupomAplicado;
	}

	public BigDecimal calculaValorTotalComDesconto() {

		BigDecimal valorTotalPedido = this.pedido.getValorTotalPedido();

		if (cupomAplicado == null) {
			return valorTotalPedido;
		}

		BigDecimal percentualDecimal = this.cupomAplicado.getPercentualMomento().divide(BigDecimal.valueOf(100));

		BigDecimal multiplicador = BigDecimal.ONE.subtract(percentualDecimal);

		BigDecimal valorFinal = valorTotalPedido.multiply(multiplicador);

		return valorFinal;

	}

	public Compra cloneComDadosAtualizadosDoCupomPedido(Pedido pedido, CupomAplicado cupomAplicado) {
		Compra compraAtual = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, estado,
				telefone, cep, this.cupomAplicado, this.pedido);
	
		compraAtual.cupomAplicado = cupomAplicado;
		compraAtual.pedido = pedido;
		
		return compraAtual;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", pais=" + pais + ", estado=" + estado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

}
