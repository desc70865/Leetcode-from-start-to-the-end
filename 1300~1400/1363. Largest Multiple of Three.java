/* 
Given an integer array of digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order.

Since the answer may not fit in an integer data type, return the answer as a string.

If there is no answer return an empty string.

 

Example 1:

Input: digits = [8,1,9]
Output: "981"
Example 2:

Input: digits = [8,6,7,1,0]
Output: "8760"
Example 3:

Input: digits = [1]
Output: ""
Example 4:

Input: digits = [0,0,0,0,0,0]
Output: "0"
 

Constraints:

1 <= digits.length <= 10^4
0 <= digits[i] <= 9
The returning answer must not contain unnecessary leading zeros.
 */

class Solution {
    int size;

    public String largestMultipleOfThree(int[] digits) {
        int[] map = new int[10];
        for (int digit: digits) {
            map[digit]++;
        }
        int[] rem = new int[3];
        for (int i = 0; i < 10; i++) {
            rem[i % 3] += map[i];
        }
        if (rem[0] == 0 && (rem[1] == 0 && rem[2] < 3 || rem[1] < 3 && rem[2] == 0)) return "";
        int over = (rem[1] * 1 + rem[2] * 2) % 3;
        size = digits.length;
        if (over != 0) {
            subtract(map, rem[over] == 0 ? 3 - over : over, rem[over] == 0 ? 2 : 1);
        }
        if (map[0] == size) return "0";
        char[] ans = new char[size];
        int k = 0;
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < map[i]; j++) {
                ans[k++] = (char) (i + '0');
            }
        }
        return new String(ans);
    }

    private void subtract(int[] map, int k, int times) {
        size -= times;
        for (int i = k; i < 10 && times > 0; i += 3) {
            while (map[i] > 0 && times > 0) {
                map[i]--;
                times--;
            }
        }
    }
}