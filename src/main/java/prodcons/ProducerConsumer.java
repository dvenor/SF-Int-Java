package prodcons;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
  public static void main(String[] args) {
    // need a shared BlockingQueue<int[]>
    BlockingQueue<int[]> bq = new ArrayBlockingQueue<>(10);

    Runnable producer = () -> {
/*
      for a count of 1 to 10_000
      1) create an array of {0, count} -- this is considered transactionally "invalid"
      2) if count < 500 pause for a short time probably sleep(1)
      3) set array to be {count, count} -- i.e. "transactionally valid"
      3a) if count = 5_000 set array to { -1, count }
      4) put this into the queue
      5) overwrite our array pointer with null
      6) loop around
*/
      try {
        for (int i = 0; i < 10_000; i++) {
          int[] data = {0, i};
          if (i < 500) {
            Thread.sleep(1);
          }
          data[0] = i;
          if (i == 5_000) {
            data[0] = -1;
          }
          bq.put(data);
        }
      } catch (InterruptedException ie) {
        System.out.println("this shouldn't happen...");
      }
    };

    Runnable consumer = () -> {
/*
      for a count of 1 to 10_000
      1) take data from the queue
      2) if count > 9_500 pause...
      3) verify that the array contains { count, count } -- message if not
      4) loop around
 */
      try {
        for (int i = 0; i < 10_000; i++) {
          int [] data = bq.take();
          if (data[0] != i || data[0] != data[1]) {
            System.out.println("***** Error at index " + i + " got " + Arrays.toString(data));
          }
          if (i > 9_500) {
            Thread.sleep(1);
          }
        }
      } catch (InterruptedException ie) {
        System.out.println("shouldn't happen");
      }

    };

    // create two threads
    // start both
    // optionally--join both.
    new Thread(producer).start();
    new Thread(consumer).start();

  }
}
