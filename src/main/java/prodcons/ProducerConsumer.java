package prodcons;

public class ProducerConsumer {
  public static void main(String[] args) {
    // need a shared BlockingQueue<int[]>

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
    };

    Runnable consumer = () -> {
/*
      for a count of 1 to 10_000
      1) take data from the queue
      2) if count > 9_500 pause...
      3) verify that the array contains { count, count } -- message if not
      4) loop around
 */
    };

    // create two threads
    // start both
    // optionally--join both.
  }
}
