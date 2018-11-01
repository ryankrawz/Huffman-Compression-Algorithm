/* Huffman Compression Application
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;
import java.io.*;
import java.util.NoSuchElementException;
import java.lang.*;
import java.lang.Object;

public class Huff {

  public Map<Character, Integer> getFreqTable(String fileName){

    Map<Character, Integer> freqTable = new HashMap<Character, Integer>();

    FileReader file = openInputFile(fileName);

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
    return freqTable;
  }
  }
