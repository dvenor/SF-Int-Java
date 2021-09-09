package superiterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  private Student(String name, double gpa, List<String> courses) {
    if (!isValidStudent(name, gpa)) throw new IllegalArgumentException();
    this.name = name;
    this.gpa = gpa;
    this.courses = new ArrayList<>(courses);
  }
  private Student(String name, double gpa, String ... courses) {
    if (!isValidStudent(name, gpa)) throw new IllegalArgumentException();
    this.name = name;
    this.gpa = gpa;
    this.courses = List.of(courses);
  }

  public static boolean isValidStudent(String name, double gpa) {
    return ((name != null) && (gpa >= 0 && gpa <= 4.0));
  }
  public static Student of(String name, double gpa, String ... courses) {
    return new Student(name, gpa, courses);
  }
  public String getName() {
    return name;
  }
  public double getGpa() {
    return gpa;
  }
  public Student withGpa(double gpa) {
    if (isValidStudent(this.name, gpa)) {
      return new Student(this.name, gpa, this.courses);
    } else {
      throw new IllegalArgumentException("bad gpa");
    }
  }
  public List<String> getCourses() {
    return Collections.unmodifiableList(courses);
  }

  public static Predicate<Student> getSmartPredicate(/*final */double threshold) {
//    threshold++;
    return s -> s.gpa > threshold;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}
