package streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleStream {
  public static void main(String[] args) {
    List<String> ls = List.of("Fred", "Jim", "Sheila");
    Stream<String> ss = ls.stream();
    ss
        .filter(s -> s.length() > 3)
        .forEach(s -> System.out.println(s));
    ls.stream()
        .forEach(s -> System.out.println(s));

//    Stream.iterate(1, x -> x + 1)
//        .forEach(x -> System.out.println(x));

//    var result = Stream.iterate(1, x -> x + 1)
//        .limit(10)
//        .reduce(0, (a, b) -> a + b);
//        .forEach(x -> System.out.println(x));

    var result = IntStream.iterate(1, x -> x + 1)
        .limit(0)
        .reduce((a, b) -> a + b);
//    System.out.println("sum is " + result);
    result.ifPresentOrElse(x -> System.out.println("the sum is " + x),
        () -> System.out.println("There wasn't any data"));

    Map<String, String> names = Map.of("Fred", "Jones");
    String first = "Freddy"; // assume a computation

//    String last = names.get(first);
//    String message = "Dear " + last.toUpperCase();
//    System.out.println(message);

    Optional.of(names)
        .map(m -> m.get(first))
        .map(l -> "Dear " + l.toUpperCase())
        .ifPresentOrElse(x -> System.out.println(x), () -> System.out.println("No such client"));
  }
}
