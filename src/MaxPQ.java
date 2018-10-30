/*
Max Priority Queue API
Nick Hawk & Ryan Krawczyk
*/

public interface MaxPQ<T extends Comparable<T>> {

  T evictMax();
  void insert(T key);
  boolean isEmpty();
  int size();
  String toString();

}
