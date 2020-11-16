/* 
You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.

 

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
 */

class Solution {    
    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        int sum = 0;
        for (int num: nums) sum += num;
        x = sum - x;
        if (x == 0) return len;
        if (x < 0) return -1;
        int L = 0;
        int R = 0;
        sum = 0;
        int max = -1;
        while (R < len) {
            while (R < len && sum < x) {
                sum += nums[R++];
            }
            while (sum > x) {
                sum -= nums[L++];
            }
            if (sum == x) {
                max = Math.max(R - L, max);
                sum -= nums[L++];
            }
        }
        return max < 0 ? -1 : len - max;
    }
}



class Solution {    
    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int sum = preSum[len];
        if (sum == x) return len;
        if (sum < x) return -1;
        int min = len + 1;
        for (int i = 0; i <= len && preSum[i] <= x; i++) {
            if (preSum[i] == x) {
                min = Math.min(min, i);
                break;
            }
            int p = bs(preSum, i + 1, len, sum + preSum[i] - x);
            if (p >= 0) {
                min = Math.min(min, len - p + i);
            }
        }
        return min <= len ? min : -1;
    }
    
    private int bs(int[] preSum, int left, int right, int target) {
        while (left <= right) {
            int mid = left + right >> 1;
            if (preSum[mid] == target) return mid;
            else if (preSum[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}



class Solution {    
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num: nums) sum += num;
        x = sum - x;
        int len = nums.length;
        if (x == 0) return len;
        if (x < 0) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = -1;
        sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            max = Math.max(max, i - map.getOrDefault(sum - x, len));
            map.put(sum, i);
        }
        return max < 0 ? -1 : len - max;
    }
}