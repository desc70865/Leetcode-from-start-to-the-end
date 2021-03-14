/* 
Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
Note: N is an integer in the range [0, 10^9].
 */

class Solution {
    public int monotoneIncreasingDigits(int N) {
        if (N < 10) return N;
        char[] chs = String.valueOf(N).toCharArray();
        int len = chs.length;
        for (int i = 0; i < len - 1; i++) {
            if (chs[i] > chs[i + 1]) {
                chs[i]--;
                if (i > 0 && chs[i - 1] > chs[i]) {
                    i -= 2;
                    continue;
                }
                for (int j = i + 1; j < len; j++) {
                    chs[j] = '9';
                }
                return Integer.valueOf(new String(chs));
            }
        }
        return N;
    }
}