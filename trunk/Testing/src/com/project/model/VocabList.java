package com.project.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "vocab_list")
public class VocabList {
	
	@DatabaseField(id=true)
	private int id;
	
	@DatabaseField
	private String vocab;
	
	@DatabaseField
	private boolean bmk;
	
	@ForeignCollectionField(foreignFieldName="vocab", eager=false)
	private ForeignCollection<Meaning> meaning;
	
	public VocabList() {
		
	}
	
	public VocabList(int ID, String vocab, boolean bmk) {
		
		this.id = ID;
		this.vocab = vocab;
		this.bmk = bmk;
		
	}
	
	public void setID(int ID) {
		this.id = ID;
	}
	
	public void setVocab(String vocab) {
		this.vocab = vocab;
	}
	
	public void setBmk(boolean bmk) {
		this.bmk = bmk;
	}
	
	public void setMeaning(ForeignCollection<Meaning> meaning) {
		this.meaning = meaning;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getVocab() {
		return this.vocab;
	}
	
	public ForeignCollection<Meaning> getMeaning() {
		return this.meaning;
	}
	
}
