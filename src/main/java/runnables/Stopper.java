package runnables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stopper {
//  private static boolean stop = false;
  private static List<Integer> stop = new ArrayList<>(Arrays.asList(0));

  public static void main(String[] args) throws InterruptedException {
    Runnable stopper = () -> {
      System.out.println(Thread.currentThread().getName() + " worker started");
//      while (! stop)
      while (stop.get(0) == 0)
        ;
      System.out.println(Thread.currentThread().getName() + " worker ending");
    };

    Thread t1 = new Thread(stopper);
    t1.start();
    System.out.println("main has started the worker...");
    Thread.sleep(1_000);
    System.out.println("main about to set stop flag...");
//    stop = true;
    stop.set(0, 1);
    System.out.println("main set stop flag: " + stop);
  }
}
