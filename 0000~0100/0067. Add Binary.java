/* 
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 

Constraints:

Each string consists only of '0' or '1' characters.
1 <= a.length, b.length <= 10^4
Each string is either "0" or doesn't contain any leading zero.
 */

class Solution {
    public String addBinary(String a, String b) {
        String res = "";
        int m = a.length() - 1, n = b.length() - 1, carry = 0;
        while (m >= 0 || n >= 0) {
            int p = m >= 0 ? a.charAt(m--) - '0' : 0;
            int q = n >= 0 ? b.charAt(n--) - '0' : 0;
            int sum = p + q + carry;
            res = String.valueOf(sum % 2) + res;
            carry = sum / 2;
        }
        return carry == 1 ? "1" + res : res;
    }
}



class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        char[] s1 = a.toCharArray(), s2 = b.toCharArray();
        int m = s1.length - 1, n = s2.length - 1;
        int c = 0;
        while (m >= 0 || n >= 0) {
            int sum = 0;
            if (m >= 0) sum += s1[m--] == '1' ? 1 : 0;
            if (n >= 0) sum += s2[n--] == '1' ? 1 : 0;
            sum += c;
            sb.append(sum % 2);
            c = sum / 2;
        }
        if (c > 0) sb.append(c);

        sb.reverse();
        return sb.toString();
    }
}