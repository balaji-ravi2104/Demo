package democlasses;

//Here we have only primitive types , so shallow copy of the original object is copied to the new object , 
//that means the values of x and y are copied , so if we make changes in original object it doesn't the cloned object

class Point implements Cloneable {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Point clone() throws CloneNotSupportedException {
		return (Point) super.clone();
	}
}

// Deep Copy Example

class Address {
	private String city;
	private String street;

	public Address(String city, String street) {
		this.city = city;
		this.street = street;
	}

	public String getCity() {
		return city;
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

	
}

class Person implements Cloneable {
	private String name;
	private Address address;

	public Person(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public Person clone() throws CloneNotSupportedException {
		// Perform deep copy
		Person clonedPerson = (Person) super.clone();
		clonedPerson.address = new Address(this.address.getCity(), this.address.getStreet());
		return clonedPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
}

public class ObjectCloningExample {
	public static void main(String[] args) {
		Point original = new Point(10, 20);

		try {
			// Perform object cloning
			Point clone = original.clone();

			// Modify the original object
			original.x = 100;

			// Display original and cloned objects
			System.out.println("Original: x = " + original.x + ", y = " + original.y);
			System.out.println("Clone: x = " + clone.x + ", y = " + clone.y);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		
		// Here this is example for deep copy ,because we have reference type.
		
//		Address address = new Address("New York", "123 Main St");
//        Person original = new Person("Alice", address);
//
//        try {
//            // Perform object cloning
//            Person clone = original.clone();
//
//            // Modify the original object
//            original.getAddress().setCity("Los Angeles");
//
//            // Display original and cloned objects
//            System.out.println("Original: " + original.getName() + ", " + original.getAddress().getCity());
//            System.out.println("Clone: " + clone.getName() + ", " + clone.getAddress().getCity());
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
	}
}
