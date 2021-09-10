package runnables;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Counter {
  public static long count = 0;
//  public static AtomicLong count = new AtomicLong();

  public static void main(String[] args) throws InterruptedException {
    Runnable r = () -> {
      for (int i = 0; i < 1_000_000_000L; i++) {
        synchronized (Counter.class) {
          count++;
        }
//        count.incrementAndGet();
      }
    };

    System.out.println("count is " + count);
    long start = System.nanoTime();
    Thread t = new Thread(r);
    t.start();
    Thread t2 = new Thread(r);
    t2.start();

    // "happens-before" says "if you look then you will see"..
    // join creates a happens-before relationship from the end of the thread (that dies)
    // to the continuation of the calling thread
    t.join();
    t2.join();
    long time = System.nanoTime() - start;
    System.out.println("count is " + count + ". Time was " + (time / 1_000_000_000.0));

  }
}
