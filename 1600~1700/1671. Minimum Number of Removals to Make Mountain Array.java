/* 
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.

 

Example 1:

Input: nums = [1,3,1]
Output: 0
Explanation: The array itself is a mountain array so we do not need to remove any elements.
Example 2:

Input: nums = [2,1,1,5,6,2,3,1]
Output: 3
Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
Example 3:

Input: nums = [4,3,2,1,1,2,3,1]
Output: 4
Example 4:

Input: nums = [1,2,3,4,4,3,2,1]
Output: 1
 

Constraints:

3 <= nums.length <= 1000
1 <= nums[i] <= 109
It is guaranteed that you can make a mountain array out of nums.
 */

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int len = nums.length;
        int[] l = new int[len];
        int[] r = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) l[i] = Math.max(l[i], l[j]);
            }
            l[i]++;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j > i; j--) {
                if (nums[i] > nums[j]) r[i] = Math.max(r[i], r[j]);
            }
            r[i]++;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (l[i] < 2 || r[i] < 2) continue;
            res = Math.max(res, l[i] + r[i] - 1);
        }
        return len - res;
    }
}



class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int len = nums.length;
        int max = 0;
        for (int i = 1; i < len - 1; i++) {
            int l = getLen(nums, i, 1);
            if (l == 0) continue;
            int r = getLen(nums, i, -1);
            if (r == 0) continue;
            max = Math.max(max, l + r + 1);
        }
        if (max == 0) return -1;
        return len - max;
    }
    
    private int getLen(int[] nums, int mid, int order) {
        int len = nums.length;
        int[] dp = new int[len];
        int R = 0;
        for (int i = order == 1 ? 0 : len - 1; i != mid; i += order) {
            int idx = Arrays.binarySearch(dp, 0, R, nums[i]);
            if (idx < 0) idx = - (idx + 1);
            dp[idx] = nums[i];
            if (idx == R) R++;
        }
        while (R > 0 && dp[R - 1] >= nums[mid]) R--;
        return R;
    }
}