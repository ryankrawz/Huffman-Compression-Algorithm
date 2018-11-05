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
        }
        return output;
    }

    public Map<Integer, SymbolInfo> updateBits(Map<Integer, SymbolInfo> map) {
        Integer[] keyArray = (Integer[]) map.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            // recursive walk to locate key in Huffman tree and delinate bit pattern
            // call addPattern and addLength on map.get(keyArray[i]), then map.put
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
