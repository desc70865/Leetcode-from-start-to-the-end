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
        if (num == 0) return 0;
        int len = (int) Math.log10(num) + 1, max = len - 1;
        int[] nums = new int[len], cnt = new int[len];
        for (int i = max; i >= 0; i--) {
            nums[i] = num % 10;
            num /= 10;
            if (nums[i] > nums[max]) max = i;
            cnt[i] = max;
        }
        boolean flag = false;
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (flag || nums[cnt[i]] == nums[i]) ;
            else {
                swap(nums, i, cnt[i]);
                flag = true;
            }
            res = res * 10 + nums[i];
        }
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
                swap(chars, i, maxIndex[i]);
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }
    
    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}