/* 
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
 */

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preSum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i][j - 1] + matrix[i][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                TreeSet<Integer> set = new TreeSet<>();
                int curr = 0;
                set.add(curr);
                Integer prev = null;
                for (int p = 0; p < m; p++) {
                    curr += preSum[p][r + 1] - preSum[p][l];
                    if ((prev = set.ceiling(curr - k)) != null) {
                        max = Math.max(max, curr - prev);
                    }
                    set.add(curr);
                }
            }
        }
        return max;
    }
}



class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            int[] arr = new int[m];
            for (int r = l; r < n; r++) {
                for (int i = 0; i < m; i++) {
                    arr[i] += matrix[i][r];
                }
                max = Math.max(max, maxSubArray(arr, k));
                if (max == k) return k;
            }
        }
        return max;
    }

    private int maxSubArray(int[] nums, int k) {
        int len = nums.length;
        int cur = 0, max = Integer.MIN_VALUE;
        for (int num: nums) {
            cur = Math.max(cur, 0) + num;
            max = Math.max(cur, max);
        }
        if (max <= k) return max;
        max = Integer.MIN_VALUE;
        for (int l = 0; l < len; l++) {
            int sum = 0;
            for (int r = l; r < len; r++) {
                sum += nums[r];
                if (sum < k) max = Math.max(max, sum);
                else if (sum == k) return k;
            }
        }
        return max;
    }
}



class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preSum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i][j - 1] + matrix[i][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                max = Math.max(max, maxSubArray(preSum, l, r + 1, k));
                if (max == k) return k;
            }
        }
        return max;
    }

    private int maxSubArray(int[][] preSum, int l, int r, int k) {
        int m = preSum.length;
        int cur = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            cur = Math.max(cur, 0) + preSum[i][r] - preSum[i][l];
            max = Math.max(cur, max);
        }
        if (max <= k) return max;
        max = Integer.MIN_VALUE;
        for (int a = 0; a < m; a++) {
            int sum = 0;
            for (int b = a; b < m; b++) {
                sum += preSum[b][r] - preSum[b][l];
                if (sum < k) max = Math.max(max, sum);
                else if (sum == k) return k;
            }
        }
        return max;
    }
}