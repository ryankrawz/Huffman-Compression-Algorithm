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

    private Map<Integer, SymbolInfo> table;
    private String fileName;

    public SymbolTableC(String fileName) {
        this.table = genSymbolTable(genMap(fileName));
        this.fileName = fileName;
    }

    public Map<Integer, SymbolInfo> table() { return this.table; }

    private Map<Integer, SymbolInfo> genSymbolTable(Map<Integer, SymbolInfo> freqTable) {
        HuffTree huff = genHuffTree(freqTable);
        Map<Integer, SymbolInfo> newTable = huff.updateBits(freqTable);
        return newTable;
    }

    private Map<Integer, SymbolInfo> genMap(String fileName) {
      Map<Integer, SymbolInfo> freqTable = new HashMap<Integer, SymbolInfo>();
      FileIO io = new FileIOC();
      FileReader myFile = io.openInputFile(fileName);
      try {
        if (myFile.read() == -1) { throw new NoSuchElementException("EMPTY FILE"); }
      } catch (IOException e) {
        System.out.println(e);
      }

      try {
        while (myFile.read() != -1) { // Parses through file, mapping each character to frequency
            Integer current = myFile.read();
            if (freqTable.containsKey(current)) { // if the key for the character exists, add 1 to frequency
                SymbolInfo myInfo = freqTable.get(current);
                myInfo.increment();
                freqTable.put(current, myInfo);
            }
            // if key does not exist, map it to frequency = 1
            else {
              SymbolInfo myInfo = new SymbolInfoC(1, (Bits) null);
              freqTable.put(current, myInfo);
            }
        }
      } catch (IOException e) {
        System.out.println(e);
      }
      return freqTable;
    }

    public String toString() {
      String mapper = "";
      List<Integer> l = new ArrayList<Integer>(table.keySet());
      for(int i = 0; i < l.size(); i ++) {
        mapper += String.valueOf(Character.toChars(l.get(i))) + " --> " +
        Integer.toString(table.get(l.get(i)).frequency()) + "\n";
      }
      return mapper;
    }


    private HuffTree genHuffTree(Map<Integer, SymbolInfo> freqTable) {
        PriorityQueue<HuffTree> treePQ = new PriorityQueue<HuffTree>();
        Integer[] keyArray = (Integer[]) freqTable.keySet().toArray();

        // Adds a series of one-item binary trees to the priority queue
        for (int i = 0; i < keyArray.length; i++) {
            HuffTree huff = new HuffTreeC(keyArray[i], freqTable.get(keyArray[i]).frequency());
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


    public static void main(String[] args) {

    }

}
