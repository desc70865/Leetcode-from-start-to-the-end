/* 
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 

Note:

1 <= A.length <= 30000
1 <= A[i] <= 30000
 */

class Solution {
    static final int MOD = 1_000_000_007;
    public int sumSubarrayMins(int[] A) {
        int N = A.length;
        long sum = 0;
        long res = 0;
        Deque<Integer> stack = new LinkedList<>();
        int idx = 0;
        stack.push(-1);
        int min = A[0];
        for (int i = 0; i < N; i++) {
            if (stack.size() > 1 && A[i] <= min) {
                min = A[i];
                stack = new LinkedList<>();
                stack.push(-1);
                sum = A[i] * (i + 1);
            } else {
                while (stack.size() > 1 && A[stack.peek()] >= A[i]) {
                    sum -= A[stack.peek()] * (stack.pop() - stack.peek());
                }
                sum += A[i] * (i - stack.peek());
            }
            res += sum;
            stack.push(i);
        }
        return (int) (res % MOD);
    }
}



class Solution {
    static final int MOD = 1_000_000_007;
    public int sumSubarrayMins(int[] A) {
        long res = 0, sum = 0;
        int N = A.length;
        int[] stack = new int[N + 1];
        int idx = 0;
        stack[idx++] = -1;
        for (int i = 0; i < N; i++) {
            if (A[i] <= A[stack[1]]) {
                idx = 1;
                sum = A[i] * (i + 1);
            } else {
                while (idx > 1 && A[stack[idx - 1]] >= A[i]) {
                    sum -= A[stack[idx - 1]] * (stack[idx - 1] - stack[idx - 2]);
                    idx--;
                }
                sum += A[i] * (i - stack[idx - 1]);
            }
            res += sum;
            stack[idx++] = i;
            // System.out.println(Arrays.toString(stack));
        }
        return (int) (res % MOD);
    }
}



class Solution {
    static final int MOD = 1_000_000_007;
    public int sumSubarrayMins(int[] A) {
        int N = A.length;
        int[] dp = new int[N];
        int[] preSum = new int[N];
        int R = 1;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            R = bs(dp, A, R, A[i]);
            dp[R] = i;
            if (R == 0) preSum[0] = A[i] * (i + 1);
            else preSum[R] = A[i] * (i - dp[R - 1]) + preSum[R - 1];
            sum += preSum[R++];
        }
        return (int) (sum % MOD);
    }

    private int bs(int[] dp, int[] A, int R, int k) {
        int L = 0;
        while (L < R) {
            int M = L + R >> 1;
            if (A[dp[M]] < k) L = M + 1;
            else R = M;
        }
        return L;
    }
}