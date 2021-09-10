package streams;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
  // goal: open a file (PrideAndPrejudice.txt)
  // produce (using streams :) a table of word frequency (aka concordance)
  // for the most frequently used 200 words in the book
  // in descending order
  // read the file as a Stream<String>
  // split the lines into words.. Using a regex. Pattern "\\W+"
  // remove empty strings...

  private static final Pattern NON_WORD = Pattern.compile("\\W+");

  public static void main(String[] args) throws Throwable {
    // read file as a stream
    // look at java.nio.file.Files class. Tell me how to do this!
//    try {
//      Stream<String> input = Files.lines(Path.of("PrideAndPrejudice.txt"));
//          input.forEach(s -> System.out.println(s));
//    } finally {
//      // close the file.. but it's out of scope... Simple finally kinda doesn't work well :)
//    }
    try (Stream<String> input = Files.lines(Path.of("PrideAndPrejudice.txt"));
//      FileInputStream fis = new FileInputStream("")/*;*/
      ) {

          input
              .flatMap(line -> NON_WORD.splitAsStream(line))
              .filter(s -> !s.isBlank())
              .map(s -> s.toLowerCase())
              .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
              .entrySet().stream()
              .forEach(e -> System.out.println(e));
//              .forEach(s -> System.out.println(s));
    }
  }
}
