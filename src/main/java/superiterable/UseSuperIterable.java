package superiterable;

import java.util.List;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(List.of("Fred", "Jim", "Sheila"));
    for (String s : sis) {
      System.out.println("> " + s);
    }

//    sis.withEvery(s -> System.out.println("I found the name: " + s));
    sis.forEach(s -> System.out.println("I found the name: " + s));

//    SuperIterable<String> longs = sis.getByCriterion(s -> s.length() > 3);
//    longs.withEvery(s -> System.out.println("Long: " + s));

    sis
        .filter(s -> s.length() > 3)
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println("Long: " + s));
  }
}
