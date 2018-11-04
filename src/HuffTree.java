/* Huffman Tree API
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;

public interface HuffTree {

    int compareTo(HuffTree other);
    void insert(HuffTree tree);
    int weight();
    String toString();
    Map<Integer, SymbolInfo> updateBits(Map<Integer, SymbolInfo> map);

}
