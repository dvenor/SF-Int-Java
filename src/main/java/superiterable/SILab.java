package superiterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SILab {
  private static final SuperIterable<Student> roster =
      new SuperIterable<>(List.of(
          Student.of("Fred", 3.4, "Math", "Physics"),
          Student.of("Jim", 2.4, "Journalism"),
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

  private static List<Integer> getCreditHoursForStudent(Student s) {
    List<Integer> hoursList = new ArrayList<>();
    for (String c : s.getCourses()) {
      Integer h = creditHours.get(c);
      if (h == null) h = 0;
      hoursList.add(h);
    }
    return hoursList;
  }

  // This is Java 11's nearest representation of a "tuple"
  // Java 16 introduces "records" and records are a better
  // representation of a "named tuple"
  private static class StudentWithCreditHours {
    Student s;
    int creditHours;

    public StudentWithCreditHours(Student s, int creditHours) {
      this.s = s;
      this.creditHours = creditHours;
    }
  }

  private static StudentWithCreditHours getWithCredit(Student s) {
    return new StudentWithCreditHours(s, getCreditHours(s));
  }
  public static boolean takesAdvancedCourses(Student s, int minLevel) {
    List<String> courses = s.getCourses();
    for (String c : courses) {
      Integer level = courseLevel.get(c);
      if (level != null) {
        if (level >= minLevel) return true;
      }
    }
    return false;
  }

  public static int getCreditHours(Student s) {
    int totalHours = 0;
    List<String> courses = s.getCourses();
    for (String c : courses) {
      Integer hours = creditHours.get(c);
      if (hours == null) hours = 0;
      else totalHours += hours;
    }
    return totalHours;
  }
  public static void main(String[] args) {
    System.out.println("All students");
    roster
        // we have:
        // filter -- throw away items that don't pass a test
        // map -- takes input items and produces output items
        //     -- note particularly, we can create a new value of *ANY* type
        // Add code HERE ONLY, so as to print
        // "Student: <name> has gpa: <gpa>" for all students

        .map(s -> "Student: " + s.getName() + " has gpa: " + s.getGpa())
        .forEach(s -> System.out.println(s));

    System.out.println("gpa below 3.5");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> has gpa: <gpa>" for all students with gpa less than 3.5
        .filter(s -> s.getGpa() < 3.5)
        .map(s -> "Student: " + s.getName() + " has gpa: " + s.getGpa())
        .forEach(s -> System.out.println(s));

    System.out.println("Takes more than 1 course");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> takes <count> courses" for all students taking more than 1 course
        .filter(s -> s.getCourses().size() > 1)
        .map(s -> "Student: " + s.getName() + " takes " + s.getCourses().size() + " courses")
        .forEach(s -> System.out.println(s));

    System.out.println("Studying advanced courses");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> takes advanced courses" for all students taking a course at 300+ level
        .filter(s -> takesAdvancedCourses(s, 300))
//        .filter(s -> {
//          List<String> courses = s.getCourses();
//          for (String c : courses) {
//            Integer level = courseLevel.get(c);
//            if (level != null) {
//              if (level >= 300) return true;
//            }
//          }
//          return false;
//        })
        .map(s -> "Student: " + s.getName() + "takes advanced courses")
        .forEach(s -> System.out.println(s));

    System.out.println("Course load");
    roster
        // Add code HERE ONLY, so as to print
        // "Student: <name> takes <total> credit hours" for all students
        .map(s -> getWithCredit(s))
        .map(t -> "Student: " + t.s.getName() + " takes " + t.creditHours + " credit hours")
        .forEach(s -> System.out.println(s));

    System.out.println("All courses for all students");
    roster
        // print all the courses taken by all the students.
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .forEach(s -> System.out.println(s));

    System.out.println("All credit hours for all students");
    roster
        .flatMap(s -> new SuperIterable<>(getCreditHoursForStudent(s)))
        .forEach(s -> System.out.println(s));

    System.out.println("All student-course pairs");
    // Fred takes Math
    // Fred takes Physics
    // ...
    roster
//        .flatMap(s -> {
//          return new SuperIterable<>(s.getCourses())
//              .map((String c) -> {
//                return "Student: " + s.getName() + " takes " + c;
//              });
//        })
        .flatMap(s -> new SuperIterable<>(s.getCourses())
              .map(c -> "Student: " + s.getName() + " takes " + c))
        .forEach(s -> System.out.println(s));
  }
}
