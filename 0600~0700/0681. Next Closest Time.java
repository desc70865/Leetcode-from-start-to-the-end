/* 
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 */

class Solution {
    public String nextClosestTime(String time) {
        int[] nums = new int[4];
        char[] chars = time.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i == 2) continue;
            nums[index] = chars[i] - '0';
            index++;
        }
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            minNum = Math.min(minNum, nums[i]);
        }
        boolean found = false;
        for (int i = nums.length - 1; i >= 0; i--) {
            int largerNum = Integer.MAX_VALUE;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    largerNum = Math.min(largerNum, nums[j]);
                }
            }
            if (largerNum != Integer.MAX_VALUE) {
                int temp = nums[i];
                nums[i] = largerNum;
                if (isValid(nums)) {
                    for (int j = i + 1; j < nums.length; j++) { 
                        nums[j] = minNum;
                    }
                    found = true;
                    break;
                }
                else nums[i] = temp;
            }
        }
        if (!found) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = minNum;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            builder.append(nums[i]);
            if (i == 1) builder.append(":");
        }
        return builder.toString();
    }
    
    private boolean isValid(int[] nums) {
        if (nums[0] > 2 || (nums[0] == 2 && nums[1] > 3) || nums[2] > 5) return false;
        return true;
    }
}