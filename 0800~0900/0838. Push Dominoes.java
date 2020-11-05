/* 
There are N dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.



After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.

Return a string representing the final state. 

Example 1:

Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
Example 2:

Input: "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Note:

0 <= N <= 10^5
String dominoes contains only 'L', 'R' and '.'
 */

class Solution {
    public String pushDominoes(String dominoes) {
        char[] cs = dominoes.toCharArray();
        int L = -1, R = -1;
        int len = cs.length;
        for (int i = 0; i < len; i++) {
            if (cs[i] == 'L') {
                if (L >= R) {
                    helper(cs, L + 1, i - 1, 'L');
                } else {
                    int k = (i - R - 1) / 2;
                    helper(cs, R + 1, R + k, 'R');
                    helper(cs, i - k, i - 1, 'L');
                }
                L = i;
            } else if (cs[i] == 'R') {
                if (R > L) helper(cs, R + 1, i - 1, 'R');
                R = i;
            }
        }
        if (R > L) helper(cs, R + 1, len - 1, 'R');
        return new String(cs);
    }

    private void helper(char[] s, int L, int R, char c) {
        for (int i = L; i <= R; i++) s[i] = c;
        // Arrays.fill(s, L, R + 1, c);
    }
}