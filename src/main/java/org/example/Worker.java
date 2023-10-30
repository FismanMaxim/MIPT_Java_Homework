package org.example;

public class Worker implements Runnable, Observer<EventType> {
  private static final int CHECKS_DELAY = 10;

  private boolean isAlive;
  private final Producer myProducer;

  public Worker(Producer producer) {
    myProducer = producer;

    System.out.println("New worker created");
  }

  @Override
  public void run() {
    isAlive = true;
    while (isAlive) {
      try {
        int task;
        task = myProducer.tryGetTask();

        if (task == Producer.NONE_TASK) Thread.sleep(CHECKS_DELAY);
        else {
          System.out.println("Received new task: " + task + " seconds. Starting completing...");
          Thread.sleep(task);
          System.out.println("Task \"" + task + " seconds\" completed");
        }
      } catch (InterruptedException e) {
        System.out.println("Thread was interrupted");
      }
    }
  }

  @Override
  public void HandleEvent(EventType eventType) {
    switch (eventType) {
      case TURN_OFF -> isAlive = false;
      default -> throw new IllegalStateException();
    }
  }
}
