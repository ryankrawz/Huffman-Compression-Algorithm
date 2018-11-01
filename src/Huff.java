/* Huffman Compression Application
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.io.*;
import java.util.NoSuchElementException;
import java.lang.*;
import java.lang.Object;

// Frequency Table
// 1. HashMap<Character (symbol), SymbolInfo (frequency and bit patterns)>
// 2. SymbolInfoObj.frequency is Integer and SymbolInfoObj.bits is another Object
// 3. SymbolInfoObj.bits.pattern is Integer representation of bits pattern
// 4. SymbolInfoObj.bits.length is length of string of bits

// Huffman Tree
// 1. PriorityQueue<HuffTree>
// 2. Priority queue favors minimum by accessing compareTo function in HuffTree
// 3. compareTo function should never yield a tie
// 4. Always move ties to end of peers with similar weight

public class Huff {

  public Map<Character, Integer> getFreqTable(String fileName){

    Map<Character, Integer> freqTable = new HashMap<Character, Integer>();
    File myFile = new File(fileName);

    if (myFile.exists()) { // checks to see if file exists, then proceeds
      FileReader file = new FileReader(fileName);

      if (file.read() == -1) { // Checks for empty file ***Check file.length() method/ look for a better alternative
        throw new NoSuchElementException("EMPTY FILE");
      }

      while (file.read() != -1) { // Parses through file, mapping each character to frequency

        Character currentChar = Integer.toString(file.read()).charAt(0);

        if (freqTable.containsKey(currentChar)) { // if the key for the character exists, add 1 to frequency
          Integer freq = freqTable.get(currentChar);
          freq += 1;
          freqTable.put(currentChar, freq);
        }
        else { // if key does not exist, map it to frequency = 1
          freqTable.put(currentChar, 1);
        }
      }
    }
    else {
      throw new NoSuchElementException("FILE DOES NOT EXIST");
    }
    return freqTable;
  }

  // TODO: pseudo-code, unsure if functional
  public HuffTree (Map<Character, SymbolInfo> freqTable) {
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
