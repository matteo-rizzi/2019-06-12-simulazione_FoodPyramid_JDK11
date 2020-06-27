package it.polito.tdp.food;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.IngredienteCibi;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private TextArea txtResult;

    @FXML
    private URL location;

    @FXML
    private TextField txtCalorie;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Condiment> boxIngrediente;

    @FXML
    private Button btnDietaEquilibrata;

    @FXML
    void doCalcolaDieta(ActionEvent event) {
    	this.txtResult.clear();
    	
    	if(this.boxIngrediente.getValue() == null) {
    		this.txtResult.appendText("Errore! Devi selezionare un ingrediente dall'apposito menu a tendina.\n");
    		return;
    	}
    	
    	Condiment partenza = this.boxIngrediente.getValue();
    	
    	List<Condiment> best = this.model.dietaEquilibrata(partenza);
    	this.txtResult.appendText("La dieta equilibrata è composta dai seguenti ingredienti:\n");
    	for(Condiment ingrediente : best) {
    		this.txtResult.appendText("Ingrediente: " + ingrediente + ", calorie: " + ingrediente.getCondiment_calories() + "\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Double calorie;
    	try {
    		calorie = Double.parseDouble(this.txtCalorie.getText());
    	} catch(NumberFormatException e) {
    		this.txtResult.appendText("Errore! Devi inserire un valore numerico decimale nel campo di testo 'Calorie'.\n");
    		return;
    	}
    	
    	this.model.creaGrafo(calorie);
    	
    	this.boxIngrediente.getItems().clear();
    	this.boxIngrediente.getItems().addAll(this.model.getVertici());
    	this.txtResult.appendText("Grafo creato!\n");
    	this.txtResult.appendText("# VERTICI: " + this.model.nVertici() + "\n");
    	this.txtResult.appendText("# ARCHI: " + this.model.nArchi() + "\n\n");
    	
    	List<IngredienteCibi> ingredientiCibi = this.model.getIngredientiCibi(calorie);
    	this.txtResult.appendText("Lista degli ingredienti:\n");
    	for(IngredienteCibi ic : ingredientiCibi) {
    		this.txtResult.appendText(ic + "\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxIngrediente != null : "fx:id=\"boxIngrediente\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnDietaEquilibrata != null : "fx:id=\"btnDietaEquilibrata\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
