package students2;

import students.Student;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

interface Criterion<E> {
  boolean test(E s);
}

public class School {
  public static <E> void showAll(List<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------------");
  }
  public static <E> List<E> getByCriterion(Iterable<E> ls, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.4, "Math", "Physics"),
        Student.of("Jim", 2.4, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );

    showAll(roster);

    // show smart students
//    showAll(getByCriterion(roster, ???));

    // show unenthusiastic students
//    showAll(getByCriterion(roster, ???));

    List<String> names = List.of("Alex", "James", "Fred", "Susan", "Bill");

    // show long names
//    showAll(getByCriterion(names, ???));

    // show names in second half of alphabet
//    showAll(getByCriterion(names, ???));
  }
}
