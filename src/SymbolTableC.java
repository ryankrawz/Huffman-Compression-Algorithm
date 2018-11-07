/* Symbol Table ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SymbolTableC implements SymbolTable {

    public FileIO io = new FileIOC();

    private Map<Integer, SymbolInfo> table;
    private String fileName;

    public SymbolTableC(String fileName) {
      this.fileName = fileName;
      this.table = generateFrequencyTable(this.fileName);
      addBitStrings(this.table);
    }

    public Map<Integer, SymbolInfo> table() { return this.table; }

    private Map<Integer, SymbolInfo> generateFrequencyTable(String fileName) {
      Map<Integer, SymbolInfo> freqTable = new HashMap<Integer, SymbolInfo>();
      FileReader myFile = io.openInputFile(fileName); // open input file

      try {
        Integer current = myFile.read();
        while (current != -1) { // Parses through file, mapping each character to frequency
            if (freqTable.containsKey(current)) { // if the key for the character exists, add 1 to frequency
                SymbolInfo myInfo = freqTable.get(current);
                myInfo.increment();
                freqTable.put(current, myInfo);
                current = myFile.read();
            }
            // if key does not exist, map it to frequency = 1
            else {
              SymbolInfo myInfo = new SymbolInfoC(1, "", 0);
              freqTable.put(current, myInfo);
              current = myFile.read();
            }
        }
      } catch (IOException e) {
        System.out.println(e);
      }
      return freqTable;
    }

    public void addBitStrings(Map<Integer, SymbolInfo> freqTable) { // called after frequency table is generated
        HuffTree huff = genHuffTree(freqTable); // creates a hufftree out of the frequency table
        huff.treeTraversal(freqTable, ""); // adds bits field to the symboltable
    }

    public String toString() {
      String mapper = "";
      List<Integer> l = new ArrayList<Integer>(this.table.keySet());
      for(int i = 0; i < l.size(); i ++) {
        SymbolInfo val = table.get(l.get(i));
        mapper += Integer.toString(l.get(i)) + " --> " +
        Integer.toString(val.frequency()) + " || Bit Pattern: " +
        val.pattern() + " || Bit Length: " + Integer.toString(val.length()) + "\n";
      }
      return mapper;
    }

    private HuffTree genHuffTree(Map<Integer, SymbolInfo> freqTable) {
        PriorityQueue<HuffTree> treePQ = new PriorityQueue<HuffTree>(); // create a priority queue
        List<Integer> keyArray = new ArrayList<Integer>(freqTable.keySet());

        // Adds a series of one-item binary trees to the priority queue
        for (int i = 0; i < keyArray.size(); i++) {
            HuffTree huff = new HuffTreeC(keyArray.get(i), freqTable.get(keyArray.get(i)).frequency());
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

    // unit testing
    public static void main(String[] args) {
        // make a testFile.txt for unit testing

        SymbolTable testTable = new SymbolTableC("testFile.txt");
        System.out.format("%s%n", testTable);

    }

}
