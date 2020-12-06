/* 
Given a string representing an expression of fraction addition and subtraction, you need to return the calculation result in string format. The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.

Example 1:
Input:"-1/2+1/2"
Output: "0/1"
Example 2:
Input:"-1/2+1/2+1/3"
Output: "1/3"
Example 3:
Input:"1/3-1/2"
Output: "-1/6"
Example 4:
Input:"5/3+1/3"
Output: "2/1"
Note:
The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
The number of given fractions will be in the range [1,10].
The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
 */

class Solution {
    int idx = 0;
    long molecular = 0;
    long denominator = 1;

    public String fractionAddition(String expression) {
        char[] chs = expression.toCharArray();
        int len = chs.length;
        while (idx < len) {
            add(getNext(chs));
        }
        return molecular + "/" + denominator;
    }

    private void add(long[] next) {
        molecular = molecular * next[1] + denominator * next[0];
        denominator *= next[1];
        long gcd = GCD(molecular, denominator);
        molecular /= gcd;
        denominator /= gcd;
        if (molecular > 0 && denominator < 0) {
            molecular = - molecular;
            denominator = - denominator;
        } else if (molecular < 0 && denominator < 0) {
            molecular = - molecular;
            denominator = - denominator;
        } else if (molecular == 0) {
            denominator = 1;
        }
    }

    private long GCD(long a, long b) {
        return a == 0 ? b : GCD(b % a, a);
    }

    private long[] getNext(char[] chs) {
        boolean negative = false;
        if (chs[idx] == '-') {
            idx++;
            negative = true;
        } else if (chs[idx] == '+') {
            idx++;
        }
        long nextMolecular = 0;
        while (chs[idx] != '/') {
            nextMolecular *= 10;
            nextMolecular += chs[idx++] - '0';
        }
        idx++;
        if (negative) nextMolecular = - nextMolecular;
        long nextDenominator = 0;
        while (idx < chs.length && chs[idx] >= '0') {
            nextDenominator *= 10;
            nextDenominator += chs[idx++] - '0';
        }
        // System.out.println(nextMolecular);
        // System.out.println(nextDenominator);
        return new long[] {nextMolecular, nextDenominator};
    }
}