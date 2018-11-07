/* Huffman Compression Application
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.*;
import java.util.NoSuchElementException;
import java.lang.*;
import java.lang.Object;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

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

    private static final int MAGIC_NUMBER = 0x0BC0;

    public static void main(String[] args) {
        FileIO io = new FileIOC();
        SymbolTable st = new SymbolTableC(args[0]);
        FileReader fr = io.openInputFile(args[0]); // open input file for reading

        BinaryOut out = io.openBinaryOutputFile(); // open output file for writing
        out.write(MAGIC_NUMBER, 16); // first write magic number

        List<Integer> keyList = new ArrayList<Integer>(st.table().keySet());
        int tableSize = keyList.size();
        out.write(tableSize, 32); // write SymbolTable size

        for (int i = 0; i < tableSize; i++) {
          Integer key = keyList.get(i);
          out.write(key, 8); // write key
          out.write(st.table().get(key).frequency(), 32); // write corresponding frequency
        }

        Integer current = -1;
        try                       { current = fr.read(); }
        catch (IOException e)     { System.out.println(e); }
        while (current != -1) { // loop through, converting strings to bit patterns
          int len = st.table().get(current).length();
          int jericho = 0;
          for (int j = 0; j < len; j++) {
            jericho *= 2; // regardless of reading 0 or one, multiply by 2
            if (st.table().get(current).pattern().charAt(j) == "1".charAt(0)) {
              jericho += 1; // if read a 1, add 1
            }
          }
          out.write(jericho, len); // write bit pattern to output
          try                       { current = fr.read(); }
          catch (IOException e)     { System.out.println(e); }
        }
        out.close();
      }
    }
