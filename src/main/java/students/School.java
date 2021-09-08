package students;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//interface StudentCriterion {
//  boolean test(Student s);
//}

interface Criterion<E> {
  boolean test(E s);
}

//class SmartStudent implements StudentCriterion {
class SmartStudent implements Criterion<Student> {
  @Override
  public boolean test(Student s) {
    return s.getGpa() > 3.0;
  }
}

//class EnthusiasticStudent implements StudentCriterion {
class EnthusiasticStudent implements Criterion<Student> {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

class LongString implements Criterion<String> {
  @Override
  public boolean test(String s) {
    return s.length() > 4;
  }
}

public class School {
  public static Criterion<Student> getUnenthusiastic() {
    return s -> s.getCourses().size() < 3;
  }
  public static <E> void showAll(List<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------------");
  }
//
//  public static void showAllSmart(List<Student> ls) {
//    for (Student s : ls) {
//      if (s.getGpa() > 3.0) {
//        System.out.println("> " + s);
//      }
//    }
//    System.out.println("---------------------------");
//  }

  // NONO NO NO NO! don't mutate other people's data
//  public static void removeNonSmartStudents(List<Student> ls) {
//    // remove some of them
//  }

  //  public static List<Student> getByCriterion(Iterable<Student> ls, StudentCriterion crit) {
//  public static List<Student> getByCriterion(Iterable<Student> ls, Criterion<Student> crit) {
  public static <E> List<E> getByCriterion(Iterable<E> ls, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

//  public static List<Student> getSmart(List<Student> ls, double threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
//  public static List<Student> getEnthusiastic(List<Student> ls, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

//  public static List<Student> getFairlySmart(List<Student> ls) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGpa() > 3.0) {
//        res.add(s);
//      }
//    }
//    return res;
//  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.4, "Math", "Physics"),
        Student.of("Jim", 2.4, "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );

    showAll(roster);
    showAll(getByCriterion(roster, new SmartStudent()));
    showAll(getByCriterion(roster, new EnthusiasticStudent()));
//    showAll(getSmart(roster, 3.4));
//    showAll(getSmart(roster, 3.0));
//    showAll(getEnthusiastic(roster, 3));
//    showAll(getFairlySmart(roster));

    List<String> names = List.of("Alex", "James", "Fred", "Susan", "Bill");
//    List<String> longNames = getByCriterion(names, new LongString());
    showAll(getByCriterion(names, new LongString()));
    showAll(getByCriterion(names,
        // MUST BE Criterion<String> in this case
        // might not need a classname... use "this thing here"
        // MUST be an interface that's needed
        // with exactly one abstract method
        // WE ONLY WANT to implement that one abstract method ("test")
//        (String s) -> {
//        s -> {
//      return s.charAt(0) > 'M';
//    }
        s -> s.charAt(0) > 'M'
    ));
    // NO HEADLESS ... must have empty parens for zero args -> {...}

    Object cs1 = (Criterion<String>)(s -> s.charAt(0) < 'M');

    Criterion<String> cs = s -> s.charAt(0) < 'M';
    Class<?> cl = cs.getClass();
    System.out.println("Class of cs is " + cl);
    Method[] methods = cl.getMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
    }
  }
}
