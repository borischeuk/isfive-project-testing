package com.project.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "meaning")
public class Meaning {

	@DatabaseField(id=true)
	private int id;
	
	@DatabaseField
	private String nature;
	
	@DatabaseField
	private String meaning;
	
	@DatabaseField(foreign=true)
	private VocabList vocab;
	
	public Meaning() {
		
	}
	
	public Meaning(int ID, String nature, String meaning) {
		
		this.id = ID;
		this.nature = nature;
		this.meaning = meaning;
		
	}
	
	public void setID(int ID) {
		this.id = ID;
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	public void setVocab(VocabList vocab) {
		this.vocab = vocab;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getNature() {
		return this.nature;
	}
	
	public String getMeaning() {
		return this.meaning;
	}
	
	public VocabList getVocab() {
		return this.vocab;
	}
}
