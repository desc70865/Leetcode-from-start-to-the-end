/* 
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */

class Solution {
    int N;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums) sum += num;
        if (sum % 2 == 1) return false;
        N = nums.length;
        // Arrays.sort(nums);
        return check(nums, sum / 2, 0);
    }

    private boolean check(int[] A, int k, int idx) {
        if (k == 0) return true;
        for (int i = idx; i < N; i++) {
            if (i > idx && A[i] == A[i - 1]) continue;
            if (k - A[i] < 0) return false;
            if (check(A, k - A[i], i + 1)) return true;
        }
        return false;
    }
}