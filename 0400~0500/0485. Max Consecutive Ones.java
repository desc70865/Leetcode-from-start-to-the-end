/* 
Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
 */

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, len = 0;
        for (int num: nums) {
            if (num == 0) {
                res = Math.max(res, len);
                len = 0;
            } else {
                len++;
            }
        }
        res = Math.max(res, len);
        return res;
    }
}



class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int i = 0, K = 0;
        for (int num: nums) {
            if ((K -= 1 - num) < 0) {
                K += 1 - nums[i++];
            }
        }
        return nums.length - i;
    }
}



class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int i = 0, j = 0, K = 0;
        while (j < nums.length) {
            if ((K -= 1 - nums[j++]) < 0) {
                K += 1 - nums[i++];
            }
        }
        return j - i;
    }
}