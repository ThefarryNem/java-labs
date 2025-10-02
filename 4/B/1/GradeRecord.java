import java.util.*;

class GradeRecord {
	private StudentPerson student;
	private Course course;
	private int grade;
	private Date dateRecorded;

	public GradeRecord(StudentPerson student, Course course, int grade) {
		this.student = student;
		this.course = course;
		this.grade = grade;
		this.dateRecorded = new Date();
	}

	public StudentPerson getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

	public int getGrade() {
		return grade;
	}

	public Date getDateRecorded() {
		return dateRecorded;
	}

	@Override
	public String toString() {
		return "GradeRecord{student=" + student.getName() +
				", course=" + course.getCourseName() +
				", grade=" + grade +
				", date=" + dateRecorded + "}";
	}
}
