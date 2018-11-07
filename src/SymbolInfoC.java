/* Symbol Info ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

public class SymbolInfoC implements SymbolInfo {

    private int frequency, length;
    private String pattern;

    public SymbolInfoC(int freq, String pattern, int length) {
        this.frequency = freq;
        this.pattern = pattern;
        this.length = length;
    }

    public int frequency() { return this.frequency; }

    public String pattern() { return this.pattern; }

    public int length() { return this.length; }

    public void increment() { this.frequency++; }

    public void addPattern(String pattern) { this.pattern = pattern; }

    public void addLength(int length) { this.length = length; }

}
