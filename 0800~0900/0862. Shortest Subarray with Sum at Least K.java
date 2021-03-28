/* 
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

 

Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3
 

Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
 */

class Solution {
    public int shortestSubarray(int[] A, int K) {
        int cur = 0, max = Integer.MIN_VALUE;
        int len = A.length;
        int[] preSum = new int[len + 1];
        int idx = 0;
        for (int num: A) {
            if (num >= K) return 1;
            preSum[idx + 1] = preSum[idx++] + num;
            cur = Math.max(cur, 0) + num;
            max = Math.max(cur, max);
        }
        if (max < K) return -1;
        int min = len;
        int[] deque = new int[len + 1];
        for (int i = 0, l = 0, r = -1; i <= len; i++) {
            while (l <= r && preSum[deque[r]] >= preSum[i]) {
                r--;
            }
            while (l <= r && preSum[i] - preSum[deque[l]] >= K) {
                min = Math.min(min, i - deque[l++]);
            }
            deque[++r] = i;
        }
        return min;
    }
}



class Solution {
    public int shortestSubarray(int[] A, int K) {
        int size = A.length;
        int min = size + 1;
        for (int sum = 0, left = 0, right = 0; right < size && min > 1; right++) {
            sum += A[right];
            if (sum <= 0) {
                sum = 0;
                left = right + 1;
                continue;
            }
            for (int cur = right; A[cur] < 0; cur--) {
                A[cur - 1] += A[cur];
                A[cur] = 0;
            }
            if (sum < K) continue;
            while (left < right && sum - A[left] >= K) {
                sum -= A[left++];
            }
            min = Math.min(min, right - left + 1);
        }
        return min < size + 1 ? min : -1;
    }
}