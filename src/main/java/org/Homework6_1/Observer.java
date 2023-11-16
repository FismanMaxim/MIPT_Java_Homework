package org.Homework6_1;

public interface Observer<T> {
  void HandleEvent(T eventType);
}
