/* Huffman Tree API
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;

public interface HuffTree extends Comparable {

    int compareTo(Object other);
    void insert(HuffTree tree);
    void setParent(HuffTree tree);
    HuffTree left();
    HuffTree right();
    int weight();
    Integer symbol();
    String toString();
  //  Map<Integer, SymbolInfo> updateBits(Map<Integer, SymbolInfo> map);
    void treeTraversal(Map<Integer, SymbolInfo> map, String pattern);

}
