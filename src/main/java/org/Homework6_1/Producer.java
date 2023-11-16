package org.Homework6_1;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Producer implements Runnable, Observable<EventType> {
  public static final int NONE_TASK = -1;
  private static final int ONE_SECOND = 1000;
  private static final int MIN_DELAY = 100;
  private static final int MAX_DELAY = 3000;

  private final BlockingQueue<Integer> tasks;
  private final Random random;
  private final List<Worker> workers;
  private boolean isAlive;


  public Producer(int countWorkers, int lifetime) {
    tasks = new ArrayBlockingQueue<>(countWorkers);
    random = new Random();

    // Producer generates workers that will complete its tasks
    workers = new CopyOnWriteArrayList<>();
    for (int i = 0; i < countWorkers; i++) {
      Worker worker = new Worker(this);
      Thread workerThread = new Thread(worker);
      workerThread.start();
      workers.add(worker);
    }

    Thread lifetimeThread = new Thread(() -> {
      try {
        Thread.sleep(lifetime);
      } catch (InterruptedException e) {
        System.out.println("Thread interrupted");
      }

      stopProduction();
    });
    lifetimeThread.start();
  }

  @Override
  public void run() {
    isAlive = true;
    while (isAlive) {
      try {
        int task = getRandomTask();
        System.out.println("New task generated: " + task + " seconds");
        tasks.put(task);

        Thread.sleep(ONE_SECOND);
      } catch (InterruptedException e) {
        System.out.println("Thread interrupted");
      }
    }
  }

  @Override
  public void NotifyListeners(EventType eventType) {
    for (Worker worker : workers) {
      worker.HandleEvent(eventType);
    }
  }

  /**
   * Tries to pop an element from the queue of tasks
   * @return Task (positive number in milliseconds) to complete; NONE_TASK if the queue is empty
   */
  public int tryGetTask() throws InterruptedException {
    synchronized (tasks) {
      if (tasks.isEmpty())
        return NONE_TASK;
      else
        return tasks.take();
    }
  }

  /**
   * Stops task generation and notifies listeners (workers) to stop receiving tasks
   */
  private void stopProduction () {
    isAlive = false;
    NotifyListeners(EventType.TURN_OFF);
  }

  private int getRandomTask() {
    return random.nextInt(MIN_DELAY, MAX_DELAY);
  }
}
