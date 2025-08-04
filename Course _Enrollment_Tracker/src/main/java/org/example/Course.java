package org.example;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private List<String> students;

    public Course(String courseCode) {
        this.courseCode = courseCode;
        this.students = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public List<String> getStudents() {
        return students;
    }

    public void enrollStudent(String studentName) {
        students.add(studentName);
    }

    public int countStudentEnrollment(String studentName) {
        int count = 0;
        for (String s : students) {
            if (s.equalsIgnoreCase(studentName)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return courseCode + " → " + students;
    }
}
