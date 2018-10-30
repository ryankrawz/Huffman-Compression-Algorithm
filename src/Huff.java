/* Huffman Compression Application
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.io.*;

public class Huff {

    public Map<Character, Integer> getFreqTable(String fileName) {}

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

    public static void main(String[] args) {}

}
