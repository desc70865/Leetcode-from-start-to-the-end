/* 
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 */

class Solution {
    public int findMaxLength(int[] nums) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int N = nums.length;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) cnt++;
            else cnt--;
            int k = map.getOrDefault(cnt, i);
            max = Math.max(max, i - k);
            map.put(cnt, k);
        }
        return max;
    }
}



class Solution {
    public int findMaxLength(int[] nums) {
        int N = nums.length;
        int sum = 0;
        int[] pre = new int[N * 2 + 1];
        Arrays.fill(pre, -2);
        pre[N] = -1;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) sum++;
            else sum--;
            if (pre[sum + N] > -2) {
                max = Math.max(max, i - pre[sum + N]);
            } else {
                pre[sum + N] = i;
            }
        }
        return max;
    }
}