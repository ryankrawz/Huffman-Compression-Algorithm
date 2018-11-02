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

    public static void main(String[] args) {
        SymbolTable table = new SymbolTableC(args[0]);
    }

}
