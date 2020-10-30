/* 
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 

Example 1:

Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199. 
             1 + 99 = 100, 99 + 100 = 199
 

Constraints:

num consists only of digits '0'-'9'.
1 <= num.length <= 35
Follow up:
How would you handle overflow for very large input integers?
 */

class Solution {
    public boolean isAdditiveNumber(String num) {
        return dfs(num.toCharArray(), num.length(), 0, 0, 0, 0);
    }

    private boolean dfs(char[] s, int N, int idx, long sum, long pre, int k) {
        if (idx == N) return k > 2;
        for (int i = idx; i < N; i++) {
            long cur = toNum(s, idx, i);
            if (cur < 0) continue;
            if (k >= 2 && cur != sum) continue;
            if (dfs(s, N, i + 1, pre + cur, cur, k + 1)) return true;
        }
        return false;
    }

    private long toNum(char[] s, int L, int R) {
        if (L < R && s[L] == '0') return -1;
        long res = 0;
        while (L <= R) {
            res *= 10;
            res += s[L++] - '0';
        }
        return res;
    }
}