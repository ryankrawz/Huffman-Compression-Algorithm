/* Symbol Table ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.io.*;

public class SymbolTableC implements SymbolTable {

    private SymbolTable table;

    public SymbolTableC(String fileName) {
        this.table = genSymbolTable(fileName);
        this.fileName = fileName;
    }

    public SymbolTable table() { return this.table; }

    private SymbolTable genSymbolTable() {

      Map<Integer, SymbolInfo> freqTable = new HashMap<Integer, SymbolInfo>();
      FileIO io = new FileIOC();
      FileReader myFile = io.openInputFile(fileName);
      if (myFile.read() == -1) { throw new NoSuchElementException("EMPTY FILE"); }

      while (myFile.read() != -1) { // Parses through file, mapping each character to frequency
          Integer current = myFile.read();
          if (freqTable.containsKey(current)) { // if the key for the character exists, add 1 to frequency
              SymbolInfo myInfo = freqTable.get(current);
              Integer currentFreq = myInfo.freq;
              currentFreq += 1;
              myInfo.freq = currentFreq;
              freqTable.put(current, myInfo);
          }
          // if key does not exist, map it to frequency = 1
          else {
            SymbolInfo myInfo = new SymbolInfoC(1, null); // I'm not sure what to put for the bits in the constructor
            freqTable.put(current, myInfo);
          }
      }
      return freqTable;
  }


    // private HuffTree genHuffTree(Map<Character, SymbolInfo> freqTable) {
    //     PriorityQueue<HuffTree> treePQ = new PriorityQueue<HuffTree>();
    //     Character[] keyArray = (Character[]) freqTable.keySet().toArray();
    //
    //     // Adds a series of one-item binary trees to the priority queue
    //     for (int i = 0; i < keyArray.length; i++) {
    //         HuffTree huff = new HuffTreeC((char) keyArray[i], freqTable.get(keyArray[i]).frequency());
    //         treePQ.add(huff);
    //     }
    //
    //     // While the priority queue has more than one element,
    //     // remove first two trees and contruct new tree for the priority queue
    //     while (treePQ.size() > 1) {
    //         HuffTree t1 = treePQ.poll();
    //         HuffTree t2 = treePQ.poll();
    //         HuffTree t3 = new HuffTreeC(null, t1.weight() + t2.weight());
    //         t3.insert(t1);
    //         t3.insert(t2);
    //         treePQ.add(t3);
    //     }
    //     return treePQ.poll();
    // }

}
