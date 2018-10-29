/*
Max Priority Queue ADT
Nick Hawk & Ryan Krawczyk
 */

import java.util.*;

public class MaxPQC<T> implements MaxPQ<T extends Comparable<T>> {

    private Node first, last;
    private int N;

    private class Node {
        T info;
        int weight;
        Node parent;
        Node left;
        Node right;
        Node(T info, Node parent, Node left, Node right) {
            this.info = info;
            this.weight = 0;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    public MaxPQC() {
        this.first = null;
        this.last = null;
        this.N = 0;
    }

    public T evictMax() {
        if (this.isEmpty()) { throw new NoSuchElementException("EMPTY QUEUE"); }
        else {
            T item = this.first.info;
            if (this.size() == 1) {
                this.first = null;
                this.last = null;
            } else if (/* this.last is single node remaining on bottom level */) {
                this.setNewLevel(this.first);
            } else if (this.size() % 2 == 1) { this.last = this.last.parent.left; }
            else {
                // If this.last is a left child
            }
        }
        this.first.info = this.last.info;
        this.N--;
        return item;
    }

    public void insert(T key) {
        if (this.isEmpty()) {
            this.first = new Node(key, null, null, null);
            this.last = this.first;
        }
    }

    public boolean isEmpty() {}

    public int size() { return this.N; }

    public String toString() {}

    private void sink(Node key) {}

    private void swim(Node key) {}

    private int level() { return Math.log(this.N) / Math.log(2); }

    private void setNewLevel(Node top) {
        if (top.right == null) {
            this.last = top;
            return;
        } else {
            return this.setNewLevel(top.right);
        }
    }

    public static void main(String[] args) {}

}