/* 
Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.

The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Any two characters may be swapped, even if they are not adjacent.

 

Example 1:

Input: s = "111000"
Output: 1
Explanation: Swap positions 1 and 4: "111000" -> "101010"
The string is now alternating.
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating, no swaps are needed.
Example 3:

Input: s = "1110"
Output: -1
 

Constraints:

1 <= s.length <= 1000
s[i] is either '0' or '1'.
 */

class Solution {
    static char[] a, b;
    
    static {
        a = new char[1000];
        b = new char[1000];
        for (int i = 0; i < 1000; ++i) {
            if (i % 2 == 0) {
                a[i] = '1';
                b[i] = '0';
            } else {
                a[i] = '0';
                b[i] = '1';
            }
        }
    }

    public int minSwaps(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int zero = cnt(t);
        int one = n - zero;
        if (Math.abs(zero - one) > 1) {
            return -1;
        }
        return zero == one ? Math.min(compare(t, a, n), compare(t, b, n))
            : (zero > one ? compare(t, b, n) : compare(t, a, n));
    }

    private int compare(char[] t, char[] m, int n) {
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (t[i] != m[i]) {
                ++ans;
            }
        }
        return ans / 2;
    }

    private int cnt(char[] t) {
        int ans = 0;
        for (char c: t) {
            if (c == '0') {
                ++ans;
            }
        }
        return ans;
    }
}