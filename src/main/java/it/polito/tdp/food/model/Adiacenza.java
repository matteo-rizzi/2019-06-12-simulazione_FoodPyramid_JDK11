package it.polito.tdp.food.model;

public class Adiacenza {

	private Condiment primo;
	private Condiment secondo;
	private Integer peso;

	public Adiacenza(Condiment primo, Condiment secondo, Integer peso) {
		super();
		this.primo = primo;
		this.secondo = secondo;
		this.peso = peso;
	}

	public Condiment getPrimo() {
		return primo;
	}

	public void setPrimo(Condiment primo) {
		this.primo = primo;
	}

	public Condiment getSecondo() {
		return secondo;
	}

	public void setSecondo(Condiment secondo) {
		this.secondo = secondo;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
