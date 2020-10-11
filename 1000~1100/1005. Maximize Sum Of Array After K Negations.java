/* 
Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)

Return the largest possible sum of the array after modifying it in this way.

 

Example 1:

Input: A = [4,2,3], K = 1
Output: 5
Explanation: Choose indices (1,) and A becomes [4,-2,3].
Example 2:

Input: A = [3,-1,0,2], K = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
Example 3:

Input: A = [2,-3,-1,5,-4], K = 2
Output: 13
Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
 

Note:

1 <= A.length <= 10000
1 <= K <= 10000
-100 <= A[i] <= 100
 */

class Solution {
    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int N = A.length;
        int neg = 0;
        for (int num: A) {
            if (num >= 0) break;
            neg++;
        }
        int sum = 0;
        if (K <= neg) {
            for (int i = 0; i < K; i++) sum -= A[i];
            for (int i = K; i < N; i++) sum += A[i];
            return sum;
        }
        int f = (neg - K) % 2;
        for (int i = 0; i < neg; i++) sum -= A[i];
        for (int i = neg; i < N; i++) sum += A[i];
        if (f == 0) return sum;
        if (neg == 0) return sum - 2 * A[0];
        int min = Math.min(-A[neg - 1], A[neg]);
        return sum - 2 * min;
    }
}