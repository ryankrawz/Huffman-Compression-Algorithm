/* Huffman Tree ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

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

    public int compareTo(HuffTree other) {
        if (this.weight() < other.weight())     { return -1; }
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

    public Map<Integer, SymbolInfo> updateBits(Map<Integer, SymbolInfo> map) {
        Integer[] keyArray = (Integer[]) map.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            StringBuilder sb = new StringBuilder("");
            int pattern = makeBits(findIndex(keyArray[i]), sb);
            SymbolInfo newVal = map.get(keyArray[i]);
            newVal.addPattern(pattern);
            newVal.addLength(Integer.toString(pattern).length());
            map.put(keyArray[i], newVal);
        }
        return map;
    }

    private int makeBits(int n, StringBuilder start) {
        if (n == 1) {
            start.append("b0");
            start.reverse();
            int pattern = Integer.parseInt(start.toString());
            return pattern;
        }
        if (n % 2 == 0) {
            start.append("1");
            return makeBits(n / 2, start);
        } else {
            start.append("0");
            return makeBits(n / 2, start);
        }
    }

    private HuffTree itemAt(int n) {
        if (n == 1) { return this; }
        if (n % 2 == 0) { return itemAt(n / 2).left(); }
        else { return itemAt(n / 2).right(); }
    }

    private int findIndex(Integer item) {
        try {
            int i = 1;
            while (true) {
                if (itemAt(i).symbol() == item) { return i; }
                i++;
            }
        } finally {
            throw new NoSuchElementException("TREE DOES NOT CONTAIN ITEM");
        }
    }

}
