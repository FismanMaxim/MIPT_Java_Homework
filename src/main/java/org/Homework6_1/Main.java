package org.Homework6_1;

public class Main {
  /**
   * Lifetime of the program. After this time task production stops.
   */
  private static final int lifetime = 3_000;
  /**
   * Maximum number of workers that can work in parallel
   */
  private static final int countWorkers = 5;

  public static void main(String[] args) {

    Producer producer = new Producer(countWorkers, lifetime);
    Thread thread = new Thread(producer);
    thread.start();
  }
}
