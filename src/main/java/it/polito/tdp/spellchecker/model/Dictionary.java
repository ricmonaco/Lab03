package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	private Set<String> dizionario;
	
	public void loadDictionary(String language) {
		//permette di caricare in memoria 
		//il dizionario della lingua desiderata
		dizionario = new HashSet<String>();
		try {
			FileReader fr = new FileReader("src/main/resources/"+language +".txt");
				BufferedReader br = new BufferedReader(fr);
			String parola;
			while((parola = br.readLine())!= null) {
				dizionario.add(parola);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> result = new ArrayList<RichWord>();
		for(String s : inputTextList) {
			RichWord r = new RichWord(s);
			if(dizionario.contains(s))
				r.setCorretta(true);
			else {
				r.setCorretta(false);
				result.add(r);
			}
		}
		return result;
	}

}
