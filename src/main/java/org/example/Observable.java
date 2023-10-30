package org.example;

public interface Observable<T> {
  void NotifyListeners(T eventType);
}
