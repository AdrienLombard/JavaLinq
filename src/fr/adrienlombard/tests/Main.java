package fr.adrienlombard.tests;

import java.util.ArrayList;
import java.util.List;

import fr.adrienlombard.javaLinq.classes.ListFinder;
import fr.adrienlombard.javaLinq.classes.MalformedQueryException;

public class Main {

	public static void main(String[] args) {

		List<Personne> list = new ArrayList<Personne>();
		
		list.add(new Personne("Bob", 15, 182));
		list.add(new Personne("Fred", 14, 181));
		list.add(new Personne("Jamy", 22, 184));
		list.add(new Personne("Claire", 22, 171));
		list.add(new Personne("Aymeric", 23, 175));
		
		String query = "nom != Bob";
		
		ListFinder lf = new ListFinder(list, query);
		
		List<Personne> filteredList = (List<Personne>) lf.construct();
		
		try {
			list = (List<Personne>) lf.construct();
			
			for(Personne p : list) {
				System.out.println(p.getAge());
			}
			
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
