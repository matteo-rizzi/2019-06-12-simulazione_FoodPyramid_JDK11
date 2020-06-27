package it.polito.tdp.food.model;

public class IngredienteCibi implements Comparable<IngredienteCibi>{

	private Condiment ingrediente;
	private Double calorie;
	private Integer numeroCibi;

	public IngredienteCibi(Condiment ingrediente, Double calorie, Integer numeroCibi) {
		super();
		this.ingrediente = ingrediente;
		this.calorie = calorie;
		this.numeroCibi = numeroCibi;
	}

	public Condiment getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Condiment ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Double getCalorie() {
		return calorie;
	}

	public void setCalorie(Double calorie) {
		this.calorie = calorie;
	}

	public Integer getNumeroCibi() {
		return numeroCibi;
	}

	public void setNumeroCibi(Integer numeroCibi) {
		this.numeroCibi = numeroCibi;
	}

	@Override
	public String toString() {
		return "Ingrediente: " + this.ingrediente + ", calorie: " + this.calorie + ", numero cibi: " + this.numeroCibi;
	}

	@Override
	public int compareTo(IngredienteCibi other) {
		return other.getCalorie().compareTo(this.calorie);
	}

}
