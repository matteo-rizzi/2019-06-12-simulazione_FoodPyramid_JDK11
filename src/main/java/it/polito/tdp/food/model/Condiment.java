package it.polito.tdp.food.model;

public class Condiment implements Comparable<Condiment>{
	private Integer condiment_code;
	private String display_name;
	private String condiment_portion_size;
	private Double condiment_calories;
	
	public Condiment(Integer condiment_code, String display_name, String condiment_portion_size,
			Double condiment_calories) {
		super();
		this.condiment_code = condiment_code;
		this.display_name = display_name;
		this.condiment_portion_size = condiment_portion_size;
		this.condiment_calories = condiment_calories;
	}
	
	public Integer getCondiment_code() {
		return condiment_code;
	}
	public void setCondiment_id(Integer condiment_code) {
		this.condiment_code = condiment_code;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getCondiment_portion_size() {
		return condiment_portion_size;
	}
	public void setCondiment_portion_size(String condiment_portion_size) {
		this.condiment_portion_size = condiment_portion_size;
	}
	public Double getCondiment_calories() {
		return condiment_calories;
	}
	public void setCondiment_calories(Double condiment_calories) {
		this.condiment_calories = condiment_calories;
	}

	@Override
	public String toString() {
		return condiment_code + " - "+ display_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condiment_code == null) ? 0 : condiment_code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condiment other = (Condiment) obj;
		if (condiment_code == null) {
			if (other.condiment_code != null)
				return false;
		} else if (!condiment_code.equals(other.condiment_code))
			return false;
		return true;
	}

	@Override
	public int compareTo(Condiment other) {
		return this.display_name.compareTo(other.getDisplay_name());
	}
	
	
	
}
