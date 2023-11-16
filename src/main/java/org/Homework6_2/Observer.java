package org.Homework6_2;

public interface Observer<T> {
  void HandleEvent(T eventType);
}
