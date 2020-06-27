package it.polito.tdp.food.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.creaGrafo(20);
		Condiment partenza = new Condiment(75113000, "Lettuce", "1 leaf", 0.8);
		List<Condiment> best = m.dietaEquilibrata(partenza);
		System.out.println(best);
	}

}
