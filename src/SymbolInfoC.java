/* Symbol Info ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

public class SymbolInfoC implements SymbolInfo {

    private int frequency;
    private Bits bits;

    public SymbolInfoC(int freq, Bits bits) {
        this.frequency = freq;
        this.bits = bits;
    }

    public int frequency() { return this.frequency; }

    public Bits bits() { return this.bits; }

    public void increment() { this.frequency++; }

    public void addPattern(int pattern) { this.bits.addPattern(pattern); }

    public void addLength(int length) { this.bits.addLength(length); }

}
