package br.com.zup.casadocodigo.domain.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne
	private Livro livro;
	
	@ManyToOne
	private Pedido pedido;

	@NotNull
	@Positive
	private Integer quantidade;

	public ItemPedido() {
	}

	public ItemPedido(@NotNull Livro livro, @NotNull @Positive Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotalItem() {
		return livro.getPreco().multiply(BigDecimal.valueOf(quantidade));
	}

}
