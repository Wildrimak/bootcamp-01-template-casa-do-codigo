package br.com.zup.casadocodigo.api.dtos.responses;

import br.com.zup.casadocodigo.domain.models.Compra;
import br.com.zup.casadocodigo.domain.models.CupomAplicado;

public class CompraResponse {

	private String email;
	private String nome;
	private String sobrenome;
	private String documento;
	private String endereco;
	private String complemento;
	private String cidade;
	private String estado;
	private String pais;
	private String telefone;
	private String cep;
	private PedidoResponse pedido;
	private CupomAplicadoResponse cupom;

	public CompraResponse(Compra compra) {
		this.email = compra.getEmail();
		this.nome = compra.getNome();
		this.sobrenome = compra.getSobrenome();
		this.documento = compra.getDocumento();
		this.endereco = compra.getEndereco();
		this.complemento = compra.getComplemento();
		this.cidade = compra.getCidade();

		if (compra.getEstado() != null) {
			this.estado = compra.getEstado().getNome();
		}
		
		this.pais = compra.getPais().getNome();
		this.telefone = compra.getTelefone();
		this.cep = compra.getCep();
		this.pedido = new PedidoResponse(compra.getPedido());

		CupomAplicado cupomAplicado = compra.getOptionalCupomAplicado().orElse(null);

		if (cupomAplicado != null) {
			this.cupom = new CupomAplicadoResponse(cupomAplicado, compra.calculaValorTotalComDesconto());
		}
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

	public String getEstado() {
		return estado;
	}

	public String getPais() {
		return pais;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public PedidoResponse getPedido() {
		return pedido;
	}

	public CupomAplicadoResponse getCupom() {
		return cupom;
	}

}
