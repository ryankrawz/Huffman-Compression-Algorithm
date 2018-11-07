/* Huffman Tree ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;


public class HuffTreeC implements HuffTree {

    private Integer symbol;
    private int weight;
    private HuffTree parent, left, right;

    public HuffTreeC(Integer symbol, int weight) {
        this.symbol = symbol;
        this.weight = weight;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public int compareTo(Object other) {
        HuffTree tree = (HuffTree) other;
        if (this.weight() < tree.weight())     { return -1; }
        else                                    { return 1; }
    }

    public void insert(HuffTree tree) {
        tree.setParent(this);
        if (this.left == null)              { this.left = tree; }
        else if (this.right == null)        { this.right = tree; }
        else                                { throw new RuntimeException("HUFFMAN TREE ALREADY FULL"); }
    }

    public void setParent(HuffTree tree) { this.parent = tree; }

    public HuffTree left() { return this.left; }

    public HuffTree right() { return this.right; }

    public int weight() { return this.weight; }

    public Integer symbol() { return this.symbol; }

    // designed to loop until a null pointer is reached
    public String toString() {
        String output = "[ ";
        try {
            int i = 1;
            while (true) {
                if (i == 1) {
                    output += String.format("symbol: %d | weight: %d", itemAt(i).symbol(), itemAt(i).weight());
                }
                else {
                    output += String.format(" , symbol: %d | weight: %d", itemAt(i).symbol(), itemAt(i).weight());
                }
                i++;
            }
        } finally {
            output += String.format(" ]");
            return output;
        }
    }

    // public Map<Integer, SymbolInfo> updateBits(Map<Integer, SymbolInfo> map) {
    //     List<Integer> keyArray = new ArrayList<Integer>(map.keySet());
    //     for (int i = 0; i < keyArray.size(); i++) {
    //         StringBuilder sb = new StringBuilder("");
    //         int pattern = makeBits(findIndex(keyArray.get(i)), sb);
    //         SymbolInfo newVal = map.get(keyArray.get(i));
    //         newVal.addPattern(pattern);
    //         newVal.addLength(Integer.toString(pattern).length());
    //         map.put(keyArray.get(i), newVal);
    //     }
    //     return map;
    // }


    public void treeTraversal(Map<Integer, SymbolInfo> map, String pattern) { //arguments symbol table and empty string
      if (this.right() == null && this.left() == null) {
        // how do we create bit pattern?
        SymbolInfo newVal = map.get(this.symbol());
        newVal.addPattern(pattern);
        newVal.addLength(pattern.length());
        map.put(this.symbol(), newVal);
      }
      if (this.left() != null) {
        this.left().treeTraversal(map, pattern + "0");
      }
      if (this.right() != null) {
        this.right().treeTraversal(map, pattern + "1");
      } // use charAt if 0, push 0
    }


    // private int makeBits(int n, StringBuilder start) {
    //     if (n == 1) {
    //         start.append("b0");
    //         start.reverse();
    //         int pattern = Integer.parseInt(start.toString());
    //         return pattern;
    //     }
    //     if (n % 2 == 0) {
    //         start.append("1");
    //         return makeBits(n / 2, start);
    //     } else {
    //         start.append("0");
    //         return makeBits(n / 2, start);
    //     }
    // }

    private HuffTree itemAt(int n) {
        if (n == 1) { return this; }
        if (n % 2 == 0) { return itemAt(n / 2).left(); }
        else { return itemAt(n / 2).right(); }
    }

    // designed to loop until a null pointer is reached
    // private int findIndex(Integer item) {
    //     try {
    //         int i = 1;
    //         while (true) {
    //             if (itemAt(i).symbol() == item) { return i; }
    //             i++;
    //         }
    //     } finally {
    //         throw new NoSuchElementException("TREE DOES NOT CONTAIN ITEM");
    //     }
    // }

    public static void main(String[] args) {
        char a = "A".charAt(0);
        char b = "B".charAt(0);
        char c = "C".charAt(0);
        char d = "D".charAt(0);
        char e = "E".charAt(0);

        HuffTree t1 = new HuffTreeC((int) a, 2);
        HuffTree t2 = new HuffTreeC((int) b, 5);
        HuffTree t3 = new HuffTreeC((int) c, 3);
        HuffTree t4 = new HuffTreeC((int) d, 1);
        HuffTree t5 = new HuffTreeC((int) e, 4);

        HuffTree t6 = new HuffTreeC(null, t1.weight() + t2.weight());
        t6.insert(t1);
        t6.insert(t2);
        HuffTree t7 = new HuffTreeC(null, t6.weight() + t3.weight());
        t7.insert(t6);
        t7.insert(t3);
        HuffTree t8 = new HuffTreeC(null, t7.weight() + t4.weight());
        t8.insert(t7);
        t8.insert(t4);
        HuffTree t9 = new HuffTreeC(null, t8.weight() + t5.weight());
        t9.insert(t8);
        t9.insert(t5);

        System.out.format("%s%n", t9.toString());
    }

}
