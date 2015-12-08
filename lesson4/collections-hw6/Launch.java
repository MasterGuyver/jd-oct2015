package b23;
import java.util.Arrays;
public class Launch {

	public static void main(String[] args) {
	HashMap map = new HashMap();
	Person person1= new Person("Ovsky A.G.",30,"Zaporothye","Naberegna",9);
	Person person2= new Person("Motorny A.V.",30,"Zaporothye","Naberegna",15);
	Person person3 = new Person("Grisha H.H.",30,"Zaporothye","Pobedi",5);
	Person person4 = new Person();
	person4.setPerson("Georg O.G.", 25, 3000);
	person4.setAddress("Zaporothye", "Dniprovska", 7);
	map.put(person1);
	map.put(person2);
	map.put(person3);
	map.put(person4);
	System.out.println("Keys");
	System.out.println(Arrays.toString(map.getKeys()));
	System.out.println("Values on keys");
	for(int i = 0; i<map.getPersonsKey().length; i++)
	System.out.println(map.getPersonsKey()[i].toString());
	System.out.println("Values");
	map.printPersons();
	}

}
