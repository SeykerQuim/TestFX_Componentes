package modelo;

public class Estudiante {
	private final int id;
	private final String name;
	private final String surname;
	private final int age;
	public Estudiante(int id, String name, String surname, int age) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getAge() {
		return age;
	}
	
}
