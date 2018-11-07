/* Symbol Info API
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018

Contains methods:
frequency() --> returns integer representing frequency of the symbol appearing
bits() --> returns ADT with attributes pattern and length
 */

public interface SymbolInfo {

    int frequency();
    String pattern();
    int length();
    void increment();
    void addPattern(String pattern);
    void addLength(int length);

}
