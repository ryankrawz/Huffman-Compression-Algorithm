/* Bits ADT
authors: Nick Hawk & Ryan Krawczyk
November 4, 2018
 */

public class BitsC implements Bits {

    private int pattern, length;

    public BitsC(int pattern, int length) {
        this.pattern = pattern;
        this.length = length;
    }

    public int pattern() { return this.pattern; }

    public int length() { return this.length; }

    public void addPattern(int pattern) { this.pattern = pattern; }

    public void addLength(int length) { this.length = length; }

}
