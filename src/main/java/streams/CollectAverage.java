package streams;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class Average {
  private double sum;
  private long count;

  public Average() { this(0, 0); }
  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public void include(double d) {
    this.sum += d;
    this.count++;
  }

  public void merge(Average other) {
    this.sum += other.sum;
    this.count += other.count;
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}

public class CollectAverage {
  public static void main(String[] args) {
    long start = System.nanoTime();

    // See the compilations/optimizations of the running binary
    // VM option: -XX:+PrintCompilation
    // By default the parallel mode uses the CommonForkJoinPool -- which trys to use "all cores"
    // If the thread that starts the stream is itself in a ForkJoinPool, then the stream
    // uses THAT ForkJoinPool.
    ThreadLocalRandom.current().doubles(20_000_000_000L, -Math.PI, +Math.PI)
        .parallel()
        .collect(() -> new Average(), (a, d) -> a.include(d), (a1, a2) -> a1.merge(a2))
        .get()
        .ifPresentOrElse(a -> System.out.println("Average is " + a),
            () -> System.out.println("No data"));
    long time = System.nanoTime() - start;
    System.out.println("Time taken " + (time / 1_000_000_000.0));
  }
}
