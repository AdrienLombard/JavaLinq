JavaLinq
========

JavaLinq allows you to search the objects you want into a List or an ArrayList

# How to use

We first create a List of "Personne". This object contains 3 attributes : 

```java
private String nom;
private int age;
private int taille;
```

We fill our list with new "Personne"

```java
List<Personne> list = new ArrayList<Personne>();
  	
list.add(new Personne("Bob", 15, 182));
list.add(new Personne("Fred", 14, 181));
list.add(new Personne("Jamy", 22, 184));
list.add(new Personne("Claire", 22, 171));
list.add(new Personne("Aymeric", 23, 175));
```

Now, we can create a query to get the "Personne" we're interested in :

```java
String query = "nom != Bob";
```

Here, JavaLinq will return every occurrence for which "nom" differs from "Bob"

(Note : search IS NOT case-sensitive)

Now, we need to create a new ListFinder and give the list and the query previously created.

```java
ListFinder lf = new ListFinder(list, query);

List<Personne> filteredList = (List<Personne>) lf.construct();
```

By using the "construct" method, we get a "List" of "Object" which contains the results of the search.
