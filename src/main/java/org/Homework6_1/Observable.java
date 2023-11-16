package org.Homework6_1;

public interface Observable<T> {
  void NotifyListeners(T eventType);
}
