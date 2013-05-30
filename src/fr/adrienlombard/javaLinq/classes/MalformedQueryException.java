package fr.adrienlombard.javaLinq.classes;

public class MalformedQueryException extends Exception {

	public MalformedQueryException() {
		System.err.println("Malformed query");
	}
	
	public MalformedQueryException(String message) {
		System.err.println(message);
	}
	
}
