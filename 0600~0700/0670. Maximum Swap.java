/* 
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]
 */

class Solution {
    public int maximumSwap(int num) {
        
    }
}



class Solution {
    public int maximumSwap(int num) {
        if (num == 0) return 0;
        char[] chars = String.valueOf(num).toCharArray();
        int[] maxIndex = new int[chars.length];
        int max = chars.length - 1;
        for (int j = chars.length - 1; j >= 0; j--) {
            if (chars[j] - '0' > chars[max] - '0') {
                max = j;
            }
            maxIndex[j] = max;
        }
        for (int i = 0; i < chars.length; i++) {
            int iValue = chars[i] - '0';
            int maxValue = chars[maxIndex[i]] - '0';
            if (maxValue != iValue) {
                chars[i] = (char) (maxValue + '0');
                chars[maxIndex[i]] = (char) (iValue + '0');
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }
}