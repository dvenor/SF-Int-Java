package runnables;

public class Counter {
  public static long count = 0;

  public static void main(String[] args) throws InterruptedException {
    Runnable r = () -> {
      for (int i = 0; i < 100_000; i++) {
        count++;
      }
    };

    System.out.println("count is " + count);
    Thread t = new Thread(r);
    t.start();
    Thread t2 = new Thread(r);
    t2.start();

    // "happens-before" says "if you look then you will see"..
    // join creates a happens-before relationship from the end of the thread (that dies)
    // to the continuation of the calling thread
    t.join();
    t2.join();
    System.out.println("count is " + count);
  }
}
