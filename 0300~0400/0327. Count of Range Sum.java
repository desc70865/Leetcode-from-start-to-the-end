/* 
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 

Constraints:

0 <= nums.length <= 10^4
 */

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        if (len == 0) return 0;
        long[] aux = new long[len];
        aux[0] = nums[0];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0) aux[i] = aux[i - 1] + nums[i];
            if (lower <= aux[i] && aux[i] <= upper) sum++;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                long cur = aux[i] - aux[j];
                if (lower <= cur && cur <= upper) sum++;
            }
        }
        return sum;
    }
}



class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        TreeMap<Long, Integer> tree = new TreeMap<>();
        tree.put(0L, 1);
        int count = 0;
        long sum = 0L;
        for (int num: nums) {
            sum += num;
            for (int cnt: tree.subMap(sum - upper, true, sum - lower, true).values()) {
                count += cnt;
            }
            tree.merge(sum, 1, Integer::sum);
        }
        return count;
    }
}



class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum[i + 1] = sum[i] + nums[i];
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
        int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
        int ret = n1 + n2;

        // 首先统计下标对的数量
        int i = left;
        int l = mid + 1;
        int r = mid + 1;
        while (i <= mid) {
            while (l <= right && sum[l] - sum[i] < lower) {
                l++;
            }
            while (r <= right && sum[r] - sum[i] <= upper) {
                r++;
            }
            ret += r - l;
            i++;
        }

        int[] sorted = new int[right - left + 1];
        int p1 = left, p2 = mid + 1;
        int p = 0;
        while (p1 <= mid || p2 <= right) {
            if (p1 > mid) {
                sorted[p++] = (int) sum[p2++];
            } else if (p2 > right) {
                sorted[p++] = (int) sum[p1++];
            } else {
                if (sum[p1] < sum[p2]) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    sorted[p++] = (int) sum[p2++];
                }
            }
        }
        for (int j = 0; j < sorted.length; j++) {
            sum[left + j] = sorted[j];
        }
        return ret;
    }
}