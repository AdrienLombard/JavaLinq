package fr.adrienlombard.tests;

public class Personne {

	private String nom;
	private int age;
	private int taille;
	
	public Personne(String nom, int age, int taille) {
		this.nom = nom;
		this.age = age;
		this.taille = taille;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public String getNom() {
		return this.nom;
	}
	
}
