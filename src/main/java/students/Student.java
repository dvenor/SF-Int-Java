package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
//  public Student() {} // Ouch, this would leave things not fully validated
    if (!isValidStudent(name, gpa)) throw new IllegalArgumentException();
    this.name = name;
    this.gpa = gpa;
    // asList creates a "view" on an array...
//    this.courses = Arrays.asList(courses);
    // List.of creates a truly immutable list structure, but if the
    // elements within it are mutable objects...
    this.courses = List.of(courses);
  }

  public static boolean isValidStudent(String name, double gpa) {
    return ((name != null) && (gpa >= 0 && gpa <= 4.0));
  }
  // factory (or a builder) can support pooling (making a pool of shared objects)
  // without changing the callers
  // can also support returning subtypes, e.g. "VIPStudent"
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
//    return Collections.unmodifiableList(courses);
    return courses;
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
