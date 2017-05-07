package entity;

public class ClassCount {
	private int id;
	private String classname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public ClassCount(int id, String classname) {
		this.id = id;
		this.classname = classname;
	}

	public ClassCount() {

	}

}
