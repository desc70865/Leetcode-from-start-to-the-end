/* 
Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.

 

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
Explanation: We can use 34 and 24 to sum 58 which is less than 60.
Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation: In this case it is not possible to get a pair sum less that 15.
 

Constraints:

1 <= A.length <= 100
1 <= A[i] <= 1000
1 <= K <= 2000
 */

class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        int max = -1;
        Arrays.sort(A);
        int L = 0;
        int R = A.length - 1;
        while (L < R) {
            int cur = A[L] + A[R];
            if (cur == K - 1) return K - 1;
            else if (cur < K) {
                max = Math.max(max, cur);
                L++;
            } else {
                R--;
            }
        }
        return max;
    }
}