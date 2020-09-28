/* 
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

 

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 

Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
 */

class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int[] cnt = new int[K];
        int sum = 0, res = 0;
        for (int num: A) {
            sum += num;
            while (sum < 0) sum += K;
            cnt[sum % K]++;
        }
        // System.out.println(Arrays.toString(cnt));
        for (int i = 0; i < K; i++) {
            if (i == 0) res += calc(cnt[i] + 1);
            else res += calc(cnt[i]);
        }
        return res;
    }

    private int calc(int n) {
        return n * (n - 1) / 2;
    }
}