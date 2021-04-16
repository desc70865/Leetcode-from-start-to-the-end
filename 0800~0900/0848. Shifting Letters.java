/* 
We have a string S of lowercase letters, and an integer array shifts.

Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a'). 

For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.

Return the final string after all such shifts to S are applied.

Example 1:

Input: S = "abc", shifts = [3,5,9]
Output: "rpl"
Explanation: 
We start with "abc".
After shifting the first 1 letters of S by 3, we have "dbc".
After shifting the first 2 letters of S by 5, we have "igc".
After shifting the first 3 letters of S by 9, we have "rpl", the answer.
Note:

1 <= S.length = shifts.length <= 20000
0 <= shifts[i] <= 10 ^ 9
 */

class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        int len = shifts.length;
        long[] fuckShift = new long[len + 1];
        for (int i = len - 1; i >= 0; i--) {
            fuckShift[i] = fuckShift[i + 1] + shifts[i];
        }
        char[] chs = S.toCharArray();
        for (int i = 0; i < len; i++) {
            chs[i] = shift(chs[i], fuckShift[i]);
        }
        return new String(chs);
    }

    private char shift(char c, long step) {
        return (char) ((c - 97 + step) % 26 + 97);
    }
}