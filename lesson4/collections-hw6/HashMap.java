package b23;

import java.util.TreeSet;

public class HashMap {
	private class Entry {
		String name;
		Person person;
	}
	
	static final int SIZE = 1000;
	private Entry[] entries;
	
public HashMap() {
	this.entries = new Entry[SIZE];
}
	
public void put(Person person ) {
	if(person == null ) {
		throw new IllegalStateException("Person is null.");
	}
	else {
		int index = (person.getName().hashCode()) & (SIZE - 1);
		entries[index] = new Entry();
		entries[index].person = person;
		entries[index].name = person.getName();
	}
}

public Person get(String name) {
	int i = (name.hashCode()) & (SIZE - 1);
		if(entries[i]!=null) {
		if(name.equals(entries[i].name))
			return entries[i].person;
		}
	return null;
}

public Object[] getKeys() {
	TreeSet<String> set = new TreeSet<>();
	for(int i=0; i<SIZE; i++) {
		if(entries[i]!=null) { 
			
			set.add(entries[i].name);
		}
	}
	Object[] ms = set.toArray();
	return  ms;
}

public Person[] getPersonsKey() {
	int len = getKeys().length;
	Object[] s = getKeys();
	Person[] ps = new Person[len];
	for(int i=0; i<len; i++) {
		ps[i] = get((String)s[i]);
	}
	return ps;
}

public void printPersons() {
	for(int i=0; i<SIZE; i++) {
		if(entries[i]!=null) { 
			System.out.println(entries[i].person.toString());
		}
}
}
}