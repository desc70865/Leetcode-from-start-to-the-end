/* 
You are given an array target that consists of distinct integers and another integer array arr that can have duplicates.

In one operation, you can insert any integer at any position in arr. For example, if arr = [1,4,1,2], you can add 3 in the middle and make it [1,4,3,1,2]. Note that you can insert the integer at the very beginning or end of the array.

Return the minimum number of operations needed to make target a subsequence of arr.

A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.

 

Example 1:

Input: target = [5,1,3], arr = [9,4,2,3,4]
Output: 2
Explanation: You can add 5 and 1 in such a way that makes arr = [5,9,4,1,2,3,4], then target will be a subsequence of arr.
Example 2:

Input: target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
Output: 3
 

Constraints:

1 <= target.length, arr.length <= 105
1 <= target[i], arr[i] <= 109
target contains no duplicates.
 */

class Solution {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; ++i) {
            map.put(target[i], i);
        }
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = map.getOrDefault(arr[i], -1);
        }
        return target.length - lengthOfLIS(arr);
    }
    
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int R = 0;
        for (int num: nums) {
            if (num < 0) {
                continue;
            }
            int k = num;
            if (R == 0 || k > dp[R - 1]) {
                dp[R++] = k;
            } else {
                dp[binarySearch(dp, 0, R - 1, k)] = k;
            }
        }
        return R;
    }

    private int binarySearch(int[] arr, int l, int r, int target) {
        while (l <= r) {
            int m = l + r >> 1;
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return l;
    }
}