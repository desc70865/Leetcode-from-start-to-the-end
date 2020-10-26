/* 
Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

 

Example 1:

Input: A = [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: A = [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: A = [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].
 

Constraints:

2 <= A.length <= 1000
0 <= A[i] <= 500
 */

class Solution {
    public int longestArithSeqLength(int[] A) {
        int L = A.length;
        int[][] dp = new int[L][L];
        int[] index = new int[20001];
        int maxLength = 2;
        Arrays.fill(index, -1);
        for (int i = 0; i < L; i++) {
            Arrays.fill(dp[i], 2);
            for (int j = i + 1; j < L; j++) {
                int diff = A[i] * 2 - A[j];
                if (diff < 0 || index[diff] == -1) continue;
                dp[i][j] = dp[index[diff]][i] + 1;
                maxLength = Math.max(maxLength, dp[i][j]);
            }
            index[A[i]] = i;
        }
        return maxLength;
    }
}