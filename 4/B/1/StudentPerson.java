import java.util.*;

class StudentPerson {
	private int id;
	private String name;
	private List<Course> enrolledCourses;

	public StudentPerson(int id, String name) {
		this.id = id;
		this.name = name;
		this.enrolledCourses = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Course> getEnrolledCourses() {
		return enrolledCourses;
	}

	public void enrollInCourse(Course course) {
		if (!enrolledCourses.contains(course)) {
			enrolledCourses.add(course);
		}
	}

	@Override
	public String toString() {
		return "Student{id=" + id + ", name='" + name + "'}";
	}
}
