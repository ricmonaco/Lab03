/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	Dictionary dictionary;
	List<String> inputTextList;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLanguages"
    private ComboBox<String> cmbLanguages; // Value injected by FXMLLoader

    @FXML // fx:id="lblErrors"
    private Label lblErrors; // Value injected by FXMLLoader

    @FXML // fx:id="lblTime"
    private Label lblTime; // Value injected by FXMLLoader

    @FXML // fx:id="txtInsert"
    private TextArea txtInsert; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtInsert.clear();
    	txtResult.clear();
    	lblErrors.setText("The text contains:");
    	lblTime.setText("Spell check completed in:");

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	dictionary = new Dictionary();
    	inputTextList = new ArrayList<String>();
    	//Leggo la stringa e creo la lista di stringhe.
    	if(cmbLanguages.getValue() == null) {
    		txtResult.setText("Seleziona una lingua!");
    		return;
    	}
    	
    	dictionary.loadDictionary(cmbLanguages.getValue());
    	
    	String text = txtInsert.getText();
    	if(text.isEmpty()) {
    		txtResult.setText("Inserisci Testo");
    		return;
    	}
    	
    	text = text.replaceAll("\n", "");
    	text = text.replaceAll("[.,\\\\/#!$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]]", "");
    	text = text.toLowerCase();
    	
    	StringTokenizer st = new StringTokenizer(text);
    	while(st.hasMoreElements()) {
    		inputTextList.add(st.nextToken());
    	}
    	
    	List<RichWord> result;
    	result = new ArrayList<RichWord>(dictionary.spellCheckText(inputTextList));
    	for(RichWord r : result) {
    		txtResult.appendText(r.getParola()+"\n");
    	}
    	
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLanguages != null : "fx:id=\"cmbLanguages\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
public void setModel(Dictionary model) {
    	
    	//txtInsert.setDisable(true);
    	//txtInsert.setText("Selezionare una lingua");
    	
    	//txtResult.setDisable(true);
    	cmbLanguages.getItems().addAll("English","Italian");
    	
    	//btnSpellCheck.setDisable(true);
    	//btnClearText.setDisable(true);
 	
    	
    	this.dictionary = model;
    }

}


