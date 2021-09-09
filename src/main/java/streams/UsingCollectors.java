package streams;

import students.Student;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

public class UsingCollectors {
  public static String letterGrade(Student s) {
    double gpa = s.getGpa();
    if (gpa > 3.75) return "A";
    if (gpa > 3.35) return "B";
    if (gpa > 2) return "C";
    return "D";
  }
  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.4, "Math", "Physics"),
        Student.of("Jim", 2.4, "Journalism"),
        Student.of("Jimmy", 2.5, "Journalism"),
        Student.of("James", 2.3, "Journalism"),
        Student.of("Jimbo", 3.5, "Journalism"),
        Student.of("Jimmina", 3.8, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );
    var map = roster.stream()
        .collect(Collectors.groupingBy(s -> letterGrade(s)));

    map.entrySet().stream()
        .map(e -> "Students with grade " + e.getKey() + " are " + e.getValue())
        .forEach(s -> System.out.println(s));

    roster.stream()
        .collect(Collectors.groupingBy(s -> letterGrade(s), Collectors.counting()))
        .entrySet().stream()
        .map(e -> e.getValue() + " students scored grade " + e.getKey())
        .forEach(s -> System.out.println(s));
  }
}
