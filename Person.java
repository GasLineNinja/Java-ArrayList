
public final class Person {

	private boolean isDivider;
	private String name;
	
	//default constructor creates a divider
	public Person() {
		isDivider = true;
	}
	
	//specifying constructor creates a Person with a name
	public Person(String name) {
		isDivider = false;
		this.name = name;
	}
	
	public boolean isDivider() {
		return isDivider;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		if (isDivider != other.isDivider)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
	
}
