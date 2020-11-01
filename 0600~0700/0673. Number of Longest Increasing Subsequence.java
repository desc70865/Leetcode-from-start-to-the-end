/* 
Given an integer array nums, return the number of longest increasing subsequences.

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.

 

Constraints:

0 <= nums.length <= 2000
-106 <= nums[i] <= 106
 */

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 0) return N;
        int[] sort = nums.clone();
        Arrays.sort(sort);
        Map<Integer, Integer> map = new HashMap<>();
        // serialize
        for (int i = 0; i < N; i++) map.put(sort[i], i + 1);

        BITree tree = new BITree(N);
        for (int num: nums) {
            int id = map.get(num);
            int[] q = tree.query(id - 1);
            tree.update(id, q[0] + 1, q[1]);
        }
        return tree.query(N)[1];
    }
}

class BITree {
    int[] sums;
    int[] lens;

    public BITree(int n) {
        sums = new int[n + 1];
        lens = new int[n + 1];
    }
    
    // update counter of length with x
    public void update(int id, int x, int c) {
        for (int i = id; i < sums.length; i += lowbit(i)) {
            if (lens[i] == x) {
                sums[i] += c;
            } else if (lens[i] < x) {
                lens[i] = x;
                sums[i] = c;
            }
        }
    }

    // find the amount of longest subsequence exist before
    public int[] query(int id) {
        int x = 0;
        int c = 1;
        for (int i = id; i >= 1; i -= lowbit(i)) {
            if (lens[i] > x) {
                c = sums[i];
                x = lens[i];
            } else if (lens[i] == x) {
                c += sums[i];
            }
        }
        return new int[] {x, c};
    }

    public int lowbit(int x) {
        return x & (- x);
    }
}