package pojo.classes;

public class Dependent {
	private int id;
	private String name;
	private int age;
	private String relationship;
	private String dependentName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public String getDependentName() {
		return dependentName;
	}

	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}

	@Override
	public String toString() {
		return "Dependent [id=" + id + ", name=" + name + ", age=" + age + ", relationship=" + relationship + "]";
	}
	
}
