/* Huffman Tree ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;

public class HuffTreeC implements HuffTree {

    private char symbol;
    private int weight;
    private HuffTree left, right;

    public HuffTreeC(char symbol, int weight) {
        this.symbol = symbol;
        this.weight = weight;
        this.left = null;
        this.right = null;
    }

    public int compareTo(HuffTree other) {
        if (this.weight < other.weight)     { return -1; }
        else                                { return 1; }
    }

    public void insert(HuffTree tree) {
        if (this.left == null)              { this.left = tree; }
        else if (this.right == null)        { this.right = tree; }
        else                                { throw new RuntimeException("FULL HUFFMAN TREE"); }
    }

    public int weight() { return this.weight; }

    public String toString() {}

    public Map<Character, SymbolInfo> updateBits(Map<Character, SymbolInfo> map) {}

}
