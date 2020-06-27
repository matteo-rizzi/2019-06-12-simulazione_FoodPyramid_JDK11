package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDAO;

public class Model {

	private FoodDAO dao;
	private Map<Integer, Condiment> idMap;
	private Graph<Condiment, DefaultWeightedEdge> grafo;
	private List<Condiment> best;

	public Model() {
		this.dao = new FoodDAO();
		this.idMap = new HashMap<>();
		this.dao.listAllCondiment(idMap);
	}

	public void creaGrafo(double calorie) {
		this.grafo = new SimpleWeightedGraph<Condiment, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getCondimentsByCalories(calorie));

		// aggiungo gli archi
		for (Adiacenza a : this.dao.getAdiacenze(calorie, this.idMap)) {
			Graphs.addEdge(this.grafo, a.getPrimo(), a.getSecondo(), a.getPeso());
		}
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<IngredienteCibi> getIngredientiCibi(double calorie) {
		List<IngredienteCibi> ic = new ArrayList<>(this.dao.getIngredientiCibi(idMap, calorie));
		Collections.sort(ic);
		return ic;
	}

	public List<Condiment> getVertici() {
		List<Condiment> ingredienti = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(ingredienti);
		return ingredienti;
	}

	public List<Condiment> dietaEquilibrata(Condiment partenza) {
		System.out.println(this.grafo);
		this.best = new ArrayList<>();
		List<Condiment> parziale = new ArrayList<>();
		parziale.add(partenza);

		this.cerca(parziale);

		return best;
	}

	private void cerca(List<Condiment> parziale) {
		System.out.println(parziale);
		// caso terminale
		if(calcolaCalorie(parziale) > calcolaCalorie(best)) {
			best = new ArrayList<>(parziale);
		}

		// caso intermedio
		for (Condiment ingrediente : this.grafo.vertexSet()) {
			if (!ingredienteDipendente(ingrediente, parziale) && !parziale.contains(ingrediente)) {
				parziale.add(ingrediente);
				this.cerca(parziale);
				parziale.remove(ingrediente);
			}
		}

	}

	private double calcolaCalorie(List<Condiment> ingredienti) {
		double calorieTotali = 0.0;
		for(Condiment c : ingredienti) {
			calorieTotali += c.getCondiment_calories();
		}
		return calorieTotali;
	}

	private boolean ingredienteDipendente(Condiment ingrediente, List<Condiment> parziale) {
		boolean trovato = false;
		for(Condiment c : parziale) {
			if(this.grafo.getEdge(c, ingrediente) != null)
				trovato = true;
		}
		return trovato;
	}

}
