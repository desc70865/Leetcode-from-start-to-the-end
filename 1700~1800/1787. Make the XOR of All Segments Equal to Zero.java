/* 
You are given an array nums​​​ and an integer k​​​​​. The XOR of a segment [left, right] where left <= right is the XOR of all the elements with indices between left and right, inclusive: nums[left] XOR nums[left+1] XOR ... XOR nums[right].

Return the minimum number of elements to change in the array such that the XOR of all segments of size k​​​​​​ is equal to zero.

 

Example 1:

Input: nums = [1,2,0,3,0], k = 1
Output: 3
Explanation: Modify the array from [1,2,0,3,0] to from [0,0,0,0,0].
Example 2:

Input: nums = [3,4,5,2,1,7,3,4,7], k = 3
Output: 3
Explanation: Modify the array from [3,4,5,2,1,7,3,4,7] to [3,4,7,3,4,7,3,4,7].
Example 3:

Input: nums = [1,2,4,1,2,5,1,2,6], k = 3
Output: 3
Explanation: Modify the array from [1,2,4,1,2,5,1,2,6] to [1,2,3,1,2,3,1,2,3].
 

Constraints:

1 <= k <= nums.length <= 2000
​​​​​​0 <= nums[i] < 210
 */

class Solution {
    static final int INF = 0x3f3f3f3f;

    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        if (k == 1) {
            int cnt = 0;
            for (int num: nums) {
                if (num != 0) {
                    ++cnt;
                }
            }
            return cnt;
        } else if (k == n) {
            int xor = 0;
            for (int num: nums) {
                xor ^= num;
            }
            return Math.min(xor, 1);
        }
        Map<Integer, Integer>[] map = new HashMap[k];
        int[] size = new int[k];
        for (int i = 0; i < k; ++i) {
            map[i] = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map[i].merge(nums[j], 1, Integer::sum);
                ++size[i];
            }
        }
        int[] dp = new int[1 << 10];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < k; ++i) {
            int min = min(dp);
            int[] next = new int[1 << 10];
            Arrays.fill(next, min + size[i]);
            for (int j = 0; j < (1 << 10); ++j) {
                if (dp[j] == INF) {
                    continue;
                }
                for (Map.Entry<Integer, Integer> entry: map[i].entrySet()) {
                    int key = entry.getKey() ^ j;
                    next[key] = Math.min(next[key], dp[j] + size[i] - entry.getValue());
                }
            }
            dp = next;
        }
        return dp[0];
    }

    private int min(int[] arr) {
        int min = INF;
        for (int e: arr) {
            min = Math.min(min, e);
        }
        return min;
    }
}