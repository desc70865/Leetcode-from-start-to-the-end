/* 
Given the array nums consisting of n positive integers. You computed the sum of all non-empty continous subarrays from the array and then sort them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 10^9 + 7.

 

Example 1:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
Output: 13 
Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13. 
Example 2:

Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
Output: 6
Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.
Example 3:

Input: nums = [1,2,3,4], n = 4, left = 1, right = 10
Output: 50
 

Constraints:

1 <= nums.length <= 10^3
nums.length == n
1 <= nums[i] <= 100
1 <= left <= right <= n * (n + 1) / 2
 */

class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        int MOD = 1_000_000_007;
        PriorityQueue<int[]> pq = new PriorityQueue<>(n, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < nums.length; i++) {
            pq.offer(new int[] {nums[i], i});
        }
        int ans = 0;
        int count = 0;
        while (++count <= right && !pq.isEmpty()) {
            int[] cur = pq.poll();
            if (count >= left) {
                ans += cur[0];
                ans %= MOD;
            }
            if (cur[1] < n - 1) {
                pq.offer(new int[]{(cur[0] + nums[cur[1] + 1]) % MOD, cur[1] + 1});
            }
        }
        return ans;
    }
}

// total = n * (n + 1) / 2
// 0 <= l <= r <= total
// [l, r] 前缀和

class Solution {
    private static final int MOD = 1_000_000_007;

    public int rangeSum(int[] nums, int n, int left, int right) {
        int minSum = Integer.MAX_VALUE;
        int maxSum = 0;
        for (int num : nums) {
            maxSum += num;
            minSum = Math.min(minSum, num);
        }
        return (int) ((sumOfFirstK(right, nums, minSum, maxSum) - sumOfFirstK(left - 1, nums, minSum, maxSum)) % MOD);
    }

    // lower bound binary search
    private long sumOfFirstK(int k, int[] nums, int minSum, int maxSum) {
        int l = minSum;
        int h = maxSum + 1;
        while (l < h) {
            int mid = (l + h) / 2;
            Node midNode = countAndTotalSum(mid, nums);
            // Lower bound
            if (midNode.count >= k) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        Node result = countAndTotalSum(l, nums);
        // Since result is computed based on lower bound, it's possible that the number of subarrays is > k
        // e.g. for array [1,2,3,4], the subarrays sum is [1,2,3,3,4...], the count for subarray sum 3 is 4
        if (result.count != k) {
            return result.totalSum - (result.count - k) * l;
        } else {
            return result.totalSum;
        }
    }

    private Node countAndTotalSum(int targetSum, int[] nums) {
        long totalSum = 0;
        int totalCount = 0;
        int subArraysSum = 0;
        int winL = 0;
        int winSum = 0;
        for (int winR = 0; winR < nums.length; winR++) {
            // Extend
            winSum += nums[winR];
            subArraysSum += (winR - winL + 1) * nums[winR];
            // Shrink
            while (winSum > targetSum) {
                subArraysSum -= winSum;
                winSum -= nums[winL];
                winL++;
            }
            totalCount += winR - winL + 1;
            totalSum += subArraysSum;
        }
        return new Node(totalCount, totalSum);
    }

    private static class Node {
        private int count;
        private long totalSum;
        private Node(int count, long totalSum) {
            this.count = count;
            this.totalSum = totalSum;
        }
    }
}