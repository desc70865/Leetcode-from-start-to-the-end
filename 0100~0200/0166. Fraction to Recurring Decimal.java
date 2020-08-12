/* 
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

If multiple answers are possible, just return any of them.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
 */

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        
    }
}



class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        int a = numerator, b = denominator;
        sb.append(a / b);
        sb.append('.');
        a %= b;
        int[] c = helper(b);
        
    }

    private int[] helper(int n) {
        int k = 1;
        while (n % 2 == 0) {
            n /= 2;
            k *= 2;
        }
        while (n % 5 == 0) {
            n /= 5;
            k *= 5;
        }
        return new int[] { n, k };
    }
}



class Solution {
    public String fractionToDecimal(int a, int b) {
        if (a == 0) return "0";
        StringBuilder sb = new StringBuilder();
        // If either one is negative (not both)
        if (a < 0 ^ b < 0) sb.append("-");
        // Convert to Long or else abs(-2147483648) overflows
        long A = Math.abs(Long.valueOf(a));
        long B = Math.abs(Long.valueOf(b));
        sb.append(String.valueOf(A / B));
        long C = A % B;
        if (C == 0) return sb.toString();
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (C != 0) {
            if (map.containsKey(C)) {
                sb.insert(map.get(C), "(");
                sb.append(")");
                break;
            }
            map.put(C, sb.length());
            C *= 10;
            sb.append(String.valueOf(C / B));
            C %= B;
        }
        return sb.toString();
    }
}