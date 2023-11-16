package org.Homework6_2;

public interface Observable<T> {
  void NotifyListeners(T eventType);
}
