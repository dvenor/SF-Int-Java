package runnables;

public class Stopper {
  private static boolean stop = false;

  public static void main(String[] args) throws InterruptedException {
    Runnable stopper = () -> {
      System.out.println(Thread.currentThread().getName() + " worker started");
      while (! stop)
        ;
      System.out.println(Thread.currentThread().getName() + " worker ending");
    };

    Thread t1 = new Thread(stopper);
    t1.start();
    System.out.println("main has started the worker...");
    Thread.sleep(1_000);
    System.out.println("main about to set stop flag...");
    stop = true;
    System.out.println("main set stop flag: " + stop);
  }
}
