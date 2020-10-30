/* 
Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 

Example 1:

Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
Example 2:

Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
Example 3:

Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 

Note:

L >= 1
M >= 1
L + M <= A.length <= 1000
0 <= A[i] <= 1000
 */

class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int N = A.length;
        int[] B = new int[N + 1];
        for (int i = 1; i <= N; i++) B[i] = B[i - 1] + A[i - 1];
        if (L + M == N) return B[N];
        if (L == M) return helper(B, L, M);
        return Math.max(helper(B, L, M), helper(B, M, L));
    }

    private int helper(int[] B, int L, int M) {
        int N = B.length - 1;
        int res = 0;
        int[] memo = new int[N];
        for (int k = N - M; k >= L; k--) {
            memo[k] = B[k + M] - B[k];
            if (k < N - M) memo[k] = Math.max(memo[k], memo[k + 1]);
        }
        for (int i = L; i <= N - M; i++) {
            res = Math.max(res, B[i] - B[i - L] + memo[i]);
        }
        return res;
    }
}