package org.example;

public interface Observer<T> {
  void HandleEvent(T eventType);
}
