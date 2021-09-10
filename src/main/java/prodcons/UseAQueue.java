package prodcons;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UseAQueue {
  public static void main(String[] args) {
    BlockingQueue<String> bqs = new ArrayBlockingQueue<>(10);
    Runnable r = () -> {
      System.out.println("worker starting");
      int delay = 2000 + (int)(Math.random() * 2000);
      try {
        Thread.sleep(delay);
        System.out.println("worker about to write message");
        bqs.put("DIE!");
        System.out.println("Message sent");
      } catch (InterruptedException e) {
        System.out.println("huh? who interrupted me!?");
      }
      System.out.println("worker finishing...");
    };

    System.out.println("main about to launch worker");
    new Thread(r).start();
    System.out.println("worker launched...");
    try {
      String msg = bqs.take();
      System.out.println("main received message: " + msg);
    } catch (InterruptedException e) {
      System.out.println("Huh? main interrupted.");;
    }
    System.out.println("main exiting....");
  }
}
