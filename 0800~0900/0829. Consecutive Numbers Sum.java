/* 
Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3
Example 2:

Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
Example 3:

Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
Note: 1 <= N <= 10 ^ 9.
 */

class Solution {
    public int consecutiveNumbersSum(int N) {
        int ans = 1;
        for (int i = 2; i * i < 2 * N; i++) {
            if ((N - i * (i - 1) / 2) % i == 0) ans++;
        }
        return ans;
    }
}



class Solution {
    public int consecutiveNumbersSum(int N) {
        while (N % 2 == 0) N >>= 1;
        int ans = 1;
        for (int d = 3; d * d <= N; d += 2) {
            int cnt = 0;
            while (N % d == 0) {
                N /= d;
                cnt++;
            }
            ans *= cnt + 1;
        }
        if (N > 1) ans <<= 1;
        return ans;
    }
}