package b23;

public class Person {
	private String name;
	private int age;
	private long salary;
	private Address address;
	
	public Person() {
		setPerson(null,0,0);
	}

	public Person(String name, int age, String city, String street, int house) {
		this.setPerson(name,age,1000);
		this.setAddress(city, street, house);
	}
	
	public void setPerson(String name, int age, int salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;	
	}
	
	public void setAddress(String city, String street, int house) {
		address = new Address(city, street, house);
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person s = (Person) obj;
			if (name.equals(s.name) && s.age == age && name != null
					&& s.salary == salary && address != null
					&& s.address.equals(address)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int res = 25;
		res = 37 * res * name.hashCode();
		res = 37 * res + age;
		res = 37 * res * ((int) (salary ^ (salary >>> 32)));
		if (address != null) {
			res = 37 * res + address.hashCode();
		}
		return res;
	}
	
	

	@Override
	public String toString() {
		String s = name + " ";
		s +="age: "+age+" ";
		s += "salary: "+salary+". Address: ";
		s +=address.toString();
		return s;
	}



	private class Address {
		private String city;
		private String street;
		private int house;

		public Address(String city, String street, int house) {
			if (city == null || street == null || house <= 0) {
				throw new IllegalStateException(
						"Parameters shoul not contains null values and number of house should be > 0");
			}
			this.city = city;
			this.street = street;
			this.house = house;
		}

		public String getCity() {
			return city;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Person) {
				Address s = (Address) obj;
				if (city.equals(s.city) && s.house == house && city != null
						&& street.equals(s.street) && street != null) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int hashCode() {
			int res = 25;
			res = 37 * res * city.hashCode();
			res = 37 * res + street.hashCode();
			res = 37 * res + house;
			return res;
		}

		
		@Override
		public String toString() {
			String res = "city: "+city;
			res+=" street: "+street;
			res+=" house: "+house;
			return res;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public int getHouse() {
			return house;
		}

		public void setHouse(int house) {
			this.house = house;
		}

	}

}
