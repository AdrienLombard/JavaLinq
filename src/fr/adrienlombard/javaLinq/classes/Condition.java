package fr.adrienlombard.javaLinq.classes;

public class Condition {

	private String operateur;
	private String operande1;
	private String operande2;
	
	public Condition() {}
	
	public Condition(String operateur, String operande1, String operande2) {
		this.operateur = operateur;
		this.operande1 = operande1;
		this.operande2 = operande2;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getOperande1() {
		return operande1;
	}

	public void setOperande1(String operande1) {
		this.operande1 = operande1;
	}

	public String getOperande2() {
		return operande2;
	}

	public void setOperande2(String operande2) {
		this.operande2 = operande2;
	}
	
}
