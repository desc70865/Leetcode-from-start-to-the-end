/* 
Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:

0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
F.length >= 3;
and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:

Input: "123456579"
Output: [123,456,579]
Example 2:

Input: "11235813"
Output: [1,1,2,3,5,8,13]
Example 3:

Input: "112358130"
Output: []
Explanation: The task is impossible.
Example 4:

Input: "0123"
Output: []
Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
Example 5:

Input: "1101111"
Output: [110, 1, 111]
Explanation: The output [11, 0, 11, 11] would also be accepted.
Note:

1 <= S.length <= 200
S contains only digits.
 */

class Solution {
    List<Integer> res;

    public List<Integer> splitIntoFibonacci(String S) {
        char[] chs = S.toCharArray();
        int len = chs.length;
        for (int i = 1; i < len - i; i++) {
            int a = getInt(chs, 0, i);
            for (int j = 1; j <= len - i - j; j++) {
                if (len - i - j < i) break;
                int b = getInt(chs, i, i + j);
                res = new ArrayList<>();
                res.add(a);
                res.add(b);
                if (isValid(chs, a, b, i + j)) return res;
            }
        }
        return new ArrayList<>();
    }

    private boolean isValid(char[] chs, int a, int b, int idx) {
        if (idx == chs.length) return true;
        int cur = 0;
        for (int i = idx; i < chs.length; i++) {
            cur *= 10;
            cur += chs[i] - '0';
            if (cur == a + b) {
                res.add(cur);
                return isValid(chs, b, cur, i + 1);
            } else if (cur > a + b) {
                return false;
            } else if (cur == 0) {
                return false;
            }
        }
        return false;
    }

    private int getInt(char[] chs, int left, int right) {
        int ans = 0;
        for (int i = left; i < right; i++) {
            ans *= 10;
            ans += chs[i] - '0';
        }
        return ans;
    }
}