package runnables;

public class Simple {
  private static int i = 0;
  public static void main(String[] args) {
    Runnable counter = () -> {
      System.out.println(Thread.currentThread().getName() + " worker starting");
      for (; i < 10_000; i++) {
        System.out.println(Thread.currentThread().getName() + " i is " + i);
      }
      System.out.println(Thread.currentThread().getName() + " worker ending");
    };

    System.out.println(Thread.currentThread().getName() + " about to start worker");
//    counter.run(); // normal synchronous execution
    Thread t1 = new Thread(counter);
    Thread t2 = new Thread(counter);
//    t1.setDaemon(true);
    t1.start();
    t2.start();
    System.out.println(Thread.currentThread().getName() + " returned from start of worker");
  }
}
