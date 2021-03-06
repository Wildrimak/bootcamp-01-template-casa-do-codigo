package br.com.zup.casadocodigo.domain.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido { // cdd: 4

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Valid
	@JsonIgnore
	@OneToOne(cascade = CascadeType.PERSIST)
	private Compra compra;

	@Size(min = 1)
	@NotNull
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedidos;

	public Pedido() {
		this.itensPedidos = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<ItemPedido> getItensPedidos() {
		return Collections.unmodifiableList(itensPedidos);
	}

	public void setItensPedidos(List<ItemPedido> itensPedidos) {
		itensPedidos.forEach(item -> { // 1
			this.addItemPedido(item);
		});
	}

	public void addItemPedido(@Valid ItemPedido itemPedido) {
		this.itensPedidos.add(itemPedido);
	}

	public BigDecimal getValorTotalPedido() {

		// 1
		BigDecimal total = this.itensPedidos.stream().map(ItemPedido::getValorTotalItem).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		return total;
	}

}
