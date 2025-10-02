import java.util.*;

class Teacher {
	private int id;
	private String name;
	private String subject;
	private List<Course> createdCourses;

	public Teacher(int id, String name, String subject) {
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.createdCourses = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSubject() {
		return subject;
	}

	public List<Course> getCreatedCourses() {
		return createdCourses;
	}

	public Course createCourse(String courseName, String description, int hours) {
		Course course = new Course(courseName, description, hours, this);
		createdCourses.add(course);
		System.out.println("Преподаватель " + name + " создал курс: " + courseName);
		return course;
	}

	public void finalizeCourse(Course course) {
		if (createdCourses.contains(course) && !course.isCompleted()) {
			course.completeCourse();
			System.out.println("Курс '" + course.getCourseName() + "' завершен преподавателем " + name);
		}
	}

	@Override
	public String toString() {
		return "Teacher{id=" + id + ", name='" + name + "', subject='" + subject + "'}";
	}
}
