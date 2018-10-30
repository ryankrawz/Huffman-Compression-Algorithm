/* Huffman Compression Application
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.util.NoSuchElementException;
import java.lang.*;
import java.lang.Object;

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

  public MaxPQ<Character> genHuffTree(Map<Character, Integer> freqTable) {
      MaxPQ<MaxPQ<Character>> treePQ = new MaxPQC<MaxPQ<Character>>();
      Object[] keyArray = (Character[]) freqTable.keySet().toArray();

      // Adds a series of one-item binary trees to the priority queue
      for (int i = 0; i < keyArray.length; i++) {
          MaxPQ<Character> symbolPQ = new MaxPQC<Character>();
          symbolPQ.insert((Character) keyArray[i], freqTable.get(keyArray[i]));
          treePQ.insert(symbolPQ, 0);
      }

      // While the priority queue has more than one element,
      // remove first two trees and contruct new tree for the priority queue
      while (treePQ.size() > 1) {
          MaxPQ<Character> t1 = treePQ.evictMax();
          MaxPQ<Character> t2 = treePQ.evictMax();
          MaxPQ<Character> t3 = new MaxPQC<Character>();
          t3.insert(null, t1.first.weight + t2.first.weight);
          t3.first.left = t1;
          t3.first.right = t2;
      }
      return treePQ.evictMax();
  }

}
