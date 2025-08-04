package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CourseEnrollmentTracker {

    public void sortCoursesAlphabetically(List<Course> courses) {
        courses.sort(Comparator.comparing(Course::getCourseCode));
    }

    public void shuffleCourses(List<Course> courses) {
        Collections.shuffle(courses);
    }

    public void printCourseReport(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void countStudentFrequency(List<Course> courses, String courseCode, String studentName) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                int count = course.countStudentEnrollment(studentName);
                System.out.println("Student '" + studentName + "' is enrolled in " + courseCode + " " + count + " times.");
                return;
            }
        }
        System.out.println("Course not found: " + courseCode);
    }
}
