package superiterable;

import students.Student;

import java.util.List;
import java.util.Map;

public class SILab {
  private static final SuperIterable<Student> roster =
      new SuperIterable<>(List.of(
      students.Student.of("Fred", 3.4, "Math", "Physics"),
      students.Student.of("Jim", 2.4, "Journalism"),
      Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
  ));
  private static final Map<String, Integer> courseLevel = Map.of(
      "Physics", 100,
      "Math", 200,
      "Journalism", 300,
      "Astrophysics", 300,
      "Quantum Mechanics", 400
      );

  private static final Map<String, Integer> creditHours = Map.of(
      "Physics", 4,
      "Math", 3,
      "Journalism", 4,
      "Astrophysics", 3,
      "Quantum Mechanics", 2
      );

  public static void main(String[] args) {
    System.out.println("All students");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> has gpa: <gpa>" for all students

        .forEach(s -> System.out.println(s));

    System.out.println("gpa below 3.5");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> has gpa: <gpa>" for all students with gpa less than 3.5

        .forEach(s -> System.out.println(s));

    System.out.println("Takes more than 1 course");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> takes <count> courses" for all students taking more than 1 course

        .forEach(s -> System.out.println(s));

    System.out.println("Studying advanced courses");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> takes advanced courses" for all students taking a course at 300+ level

        .forEach(s -> System.out.println(s));

    System.out.println("Course load");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> takes <total> credit hours" for all students

        .forEach(s -> System.out.println(s));

  }
}
