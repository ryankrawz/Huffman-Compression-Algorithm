/* Symbol Table ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SymbolTableC implements SymbolTable {

    private SymbolTable table;

    public SymbolTableC(String fileName) {
        this.table = genSymbolTable(genMap(fileName));
    }

    public SymbolTable table() { return this.table; }

    private SymbolTable genSymbolTable(Map<Character, SymbolInfo> map) {}

    // this will require restructuring with new map design
    private Map<Character, SymbolInfo> genMap(string fileName) {
        Map<Character, Integer> freqTable = new HashMap<Character, Integer>();
        FileReader file = openInputFile(fileName);
        // Checks for empty file ***Check file.length() method/ look for a better alternative
        if (file.read() == -1) { throw new NoSuchElementException("EMPTY FILE"); }

        while (file.read() != -1) { // Parses through file, mapping each character to frequency
            Character currentChar = Integer.toString(file.read()).charAt(0);
            if (freqTable.containsKey(currentChar)) { // if the key for the character exists, add 1 to frequency
                Integer freq = freqTable.get(currentChar);
                freq += 1;
                freqTable.put(currentChar, freq);
            }
            // if key does not exist, map it to frequency = 1
            else { freqTable.put(currentChar, 1); }
        }
        return freqTable;
    }

    private HuffTree genHuffTree(Map<Character, SymbolInfo> freqTable) {
        PriorityQueue<HuffTree> treePQ = new PriorityQueue<HuffTree>();
        Character[] keyArray = (Character[]) freqTable.keySet().toArray();

        // Adds a series of one-item binary trees to the priority queue
        for (int i = 0; i < keyArray.length; i++) {
            HuffTree huff = new HuffTreeC((char) keyArray[i], freqTable.get(keyArray[i]).frequency());
            treePQ.add(huff);
        }

        // While the priority queue has more than one element,
        // remove first two trees and contruct new tree for the priority queue
        while (treePQ.size() > 1) {
            HuffTree t1 = treePQ.poll();
            HuffTree t2 = treePQ.poll();
            HuffTree t3 = new HuffTreeC(null, t1.weight() + t2.weight());
            t3.insert(t1);
            t3.insert(t2);
            treePQ.add(t3);
        }
        return treePQ.poll();
    }

}
