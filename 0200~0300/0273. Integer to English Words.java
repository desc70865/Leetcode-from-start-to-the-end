/* 
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */

class Solution {
    public String numberToWords(int num) {
        
    }
}



class Solution {
    StringBuilder sb = new StringBuilder();
    
    private static final String[] WORDS = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public String numberToWords(int num) {
        fuck(num / 1_000_000_000, " Billion");
        fuck(num % 1_000_000_000 / 1_000_000, " Million");
        fuck(num % 1_000_000 / 1_000, " Thousand");
        fuck(num % 1_000, "");

        return num == 0 ? "Zero" : sb.toString();
    }
    
    private void fuck(int N, String tail) {
        if (N == 0) return ;
        append(N / 100, N % 100 / 10, N % 10);
        sb.append(tail);
    }
    
    private void append(int a, int b, int c) {
        if (a > 0) {
            addSpace();
            sb.append(WORDS[a]).append(" Hundred");
        }
        if (b > 1) {
            addSpace();
            sb.append(TENS[b]);
            if (c == 0) return ;
            sb.append(" ");
            sb.append(WORDS[c]);
        } else if (b > 0 || c > 0) {
            addSpace();
            sb.append(WORDS[b * 10 + c]);
        }
    }
    
    private void addSpace() {
        if (sb.length() > 0) sb.append(" ");
    }
}