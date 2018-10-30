/*
Max Priority Queue ADT
Nick Hawk & Ryan Krawczyk
 */

import java.util.*;
import java.lang.*;

public class MaxPQC<T extends Comparable<T>> implements MaxPQ<T> {

    private Node first, last;
    private int N;

    private class Node { // Creating Node class
        T info;
        int weight;
        Node parent;
        Node left;
        Node right;
        Node(T info, Node parent, Node left, Node right) { // triply linked
            this.info = info;
            this.weight = null;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    public MaxPQC() { // Creates priority queue and nulls pointers
        this.first = null;
        this.last = null;
        this.N = 0;
    }

    /* The algorithm accounts for three cases. If there is no maximum, hence no items,
    to evict, an exception is thrown. If there is only one item remaining, the maximum
    is evicted and all fields of MaxPQ are set to null. Otherwise, the info field of
    this.first is evicted and the info field of this.last is exchanged for it.
     */
    public T evictMax() { // returns root and swaps last leaf into roots position, then sinks
        T item;
        if (this.isEmpty()) { // empty queue, throws error
            throw new NoSuchElementException("EMPTY QUEUE");
        } else if (this.size() == 1) {
            item = this.first.info;
            this.first = null;
        }
        else {
            item = this.first.info;
            this.first.info = nodeAt(this.size()).info;
            if (this.size() % 2 == 0) { nodeAt(this.size() / 2).left = null; } // nulls out the parent's pointer to the child
            else { nodeAt(this.size() / 2).right = null; }
            sink(this.first);
        }
        this.N--;
        return item;
    }

    public void insert(T key) { // creates node in the last position and swims the info field
        if (this.isEmpty()) {
          this.first = new Node(key, null, null, null);
          this.N++;
        } else {
          this.N++;
          Node parent = nodeAt(this.size() / 2);
          if (this.N % 2 == 0) {
            parent.left = new Node(key, parent, null, null); // inserts new Node at last position
            swim(parent.left);
          } else {
            parent.right = new Node(key, parent, null, null);
            swim(parent.right);
          }
        }
    }

    public boolean isEmpty() { return this.N == 0; }

    public int size() { return this.N; }

    public String toString() {
    StringBuilder ans = new StringBuilder();
    if (this.first == null) { ans.append("{"); }
    else { ans.append("{" + first.info); }
    for (int i = 2; i <= this.size(); i++) {
        ans.append(", " + nodeAt(i).info);
    }
    ans.append("}");
    return ans.toString();
    }

    private void exchange(Node key1, Node key2) { // exchanges info fields of two nodes
        T temp = key1.info;
        key1.info = key2.info;
        key2.info = temp;
    }

    private void sink(Node key) { // compares parent to children and sinks parent if necessary
        while (key.left != null) {
            Node greatest;
            if (key.right != null && key.right.info.compareTo(key.left.info) > 0) { // compare children to find largest one
                greatest = key.right;
            }
            else {
                greatest = key.left;
            }
            if (greatest.info.compareTo(key.info) > 0) { // compare largest child to the sinker
                exchange(greatest, key);
                key = greatest;
            }
            else { break; }
        }
    }

    private void swim(Node key) { // swims an info field up the tree if it is greater than the parent
        while (key.parent != null && key.info.compareTo(key.parent.info) > 0) { // compare to the parent
            exchange(key, key.parent);
            key = key.parent;
        }
    }

    private Node nodeAt(int m) { // recursive walk through the tree to a node at a given position
        if (m == 1) return first;
        if (m % 2 == 0) return nodeAt(m / 2).left;
        else return nodeAt(m / 2).right;
    }

    public static void main(String[] args) {
        /* Unit Testing */
        // Integers
        MaxPQ<Integer> numTestPQ = new MaxPQC<Integer>();
        //Inserting
        numTestPQ.insert(3);
        numTestPQ.insert(114);
        numTestPQ.insert(56);
        numTestPQ.insert(94);
        numTestPQ.insert(169);
        numTestPQ.insert(8);

        System.out.format("%n");
        System.out.format("Priority queue after all inserts: %s%n", numTestPQ.toString());
        System.out.format("Size of priority queue: %d%n%n", numTestPQ.size());

        // Evicting
        int roof = numTestPQ.size();
        for (int i = 0; i < roof; i++) {
            System.out.format("After evicting %d: %s%n", numTestPQ.evictMax(), numTestPQ.toString());
        }
        System.out.format("Size of priority queue: %d%n%n", numTestPQ.size());

        // Strings
        MaxPQ<String> stringTestPQ = new MaxPQC<String>();
        // Inserting
        stringTestPQ.insert("D");
        stringTestPQ.insert("X");
        stringTestPQ.insert("A");
        stringTestPQ.insert("J");
        stringTestPQ.insert("N");
        stringTestPQ.insert("R");

        System.out.format("%n");
        System.out.format("Priority queue after all inserts: %s%n", stringTestPQ.toString());
        System.out.format("Size of priority queue: %d%n%n", stringTestPQ.size());

        // Evicting
        roof = stringTestPQ.size();
        for (int j = 0; j < roof; j++) {
            System.out.format("After evicting %s: %s%n", stringTestPQ.evictMax(), stringTestPQ.toString());
        }
        System.out.format("Size of priority queue: %d%n%n", stringTestPQ.size());
    }

}
