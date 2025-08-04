package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();

        Course eng101 = new Course("ENG101");
        eng101.enrollStudent("Alice");
        eng101.enrollStudent("Bob");
        eng101.enrollStudent("Alice");

        Course sci201 = new Course("SCI201");
        sci201.enrollStudent("Eve");
        sci201.enrollStudent("Frank");
        sci201.enrollStudent("Eve");

        Course com301 = new Course("COM301");
        com301.enrollStudent("Ivan");
        com301.enrollStudent("Judy");

        courses.add(eng101);
        courses.add(sci201);
        courses.add(com301);

        CourseEnrollmentTracker tracker = new CourseEnrollmentTracker();

        System.out.println("Sorted Courses : ");
        tracker.sortCoursesAlphabetically(courses);
        tracker.printCourseReport(courses);

        System.out.println("\nFrequency Count:");
        tracker.countStudentFrequency(courses, "ENG101", "Alice");

        System.out.println("\nShuffled Courses:");
        tracker.shuffleCourses(courses);
        tracker.printCourseReport(courses);
    }
}
