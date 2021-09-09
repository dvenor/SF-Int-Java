package superiterable;

import java.util.List;
import java.util.function.Predicate;

public class Weird {
  private static final SuperIterable<Student> roster =
      new SuperIterable<>(List.of(
          Student.of("Fred", 3.4, "Math", "Physics"),
          Student.of("Jim", 2.4, "Journalism"),
          Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
      ));

//  public static Predicate<Student> negate(Predicate<Student> ps) {
  public static <E> Predicate<E> negate(Predicate<E> ps) {
    return s -> !ps.test(s);
  }

  public static void main(String[] args) {
    roster
//        .filter(Student.getSmartPredicate(3.5))
        .filter(s -> {
          System.out.println("Testing a student " + s);
          return s.getGpa() > 3.5;
        })
        .forEach(s -> System.out.println(s));


    System.out.println("---------------------");

    Predicate<Student> smartPredicate = Student.getSmartPredicate(3.0);
    roster
        .filter(smartPredicate)
        .forEach(s -> System.out.println(s));
    System.out.println("---------------------");
    roster
//        .filter(negate(smartPredicate))
        .filter(smartPredicate.negate())
        .forEach(s -> System.out.println(s));
  }
}
