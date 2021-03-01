/* 
Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

 

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.
Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
 */

class Solution {
    int res = 1;
    
    public int longestSubarray(int[] nums, int limit) {
        helper(nums, 0, nums.length - 1, limit);
        return res;
    }
    
    private void helper(int[] nums, int l, int r, int limit) {
        if (l >= r) {
            return;
        }
        int m = l;
        for (int t = l; t <= r; t++) {
            if (nums[m] < nums[t]) {
                m = t;
            }
        }
        max(nums, l, r, m, limit);
        if (m - l > res) {
            helper(nums, l, m - 1, limit);
        }
        if (r - m > res) {
            helper(nums, m + 1, r, limit);
        }
    }
    
    private void max(int[] nums, int l, int r, int mid, int limit) {
        int low = mid - 1;
        int high = mid + 1;
        while (low >= l && nums[mid] - nums[low] <= limit) {
            low--;
        }
        while (high <= r && nums[mid] - nums[high] <= limit) {
            high++;
        }
        res = Math.max(res, high - low - 1);
    }
}



class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int len = nums.length;
        // Monotonic Queue
        int[] maxQueue = new int[len];
        int maxHead = 0, maxTail = -1;
        int[] minQueue = new int[len];
        int minHead = 0, minTail = -1;
        int max = 0;
        for (int l = 0, r = 0; r < len;) {
            while (maxHead <= maxTail && nums[maxQueue[maxTail]] < nums[r]) maxTail--;
            while (minHead <= minTail && nums[minQueue[minTail]] > nums[r]) minTail--;
            maxQueue[++maxTail] = minQueue[++minTail] = r++;
            while (nums[maxQueue[maxHead]] - nums[minQueue[minHead]] > limit) {
                if (l == maxQueue[maxHead]) maxHead++;
                if (l == minQueue[minHead]) minHead++;
                l++;
            }
            max = Math.max(max, r - l);
        }
        return max;
    }
}

// ???

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int l = 0, r = 0;
        int ans = 0;
        while (r < nums.length) {
            map.merge(nums[r], 1, Integer::sum);
            while (map.lastKey() - map.firstKey() > limit) {
                if (map.merge(nums[l], - 1, Integer::sum) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            ans = Math.max(ans, ++r - l);
        }
        return ans;
    }
}