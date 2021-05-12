/* 
You are given an even number of people num_people that stand around a circle and each person shakes hands with someone else, so that there are num_people / 2 handshakes total.

Return the number of ways these handshakes could occur such that none of the handshakes cross.

Since this number could be very big, return the answer mod 10^9 + 7

 

Example 1:

Input: num_people = 2
Output: 1
Example 2:



Input: num_people = 4
Output: 2
Explanation: There are two ways to do it, the first way is [(1,2),(3,4)] and the second one is [(2,3),(4,1)].
Example 3:



Input: num_people = 6
Output: 5
Example 4:

Input: num_people = 8
Output: 14
 

Constraints:

2 <= num_people <= 1000
num_people % 2 == 0
 */

class Solution {
    static Initial ans = new Initial();

    public int numberOfWays(int n) {
        return (int) ans.dp[n];
    }

    static class Initial {
        static final int MOD = 1_000_000_007;
        static long[] dp;

        static {
            dp = new long[1001];
            dp[0] = 1;
            for (int i = 2; i <= 1000; i += 2) {
                long x = 0;
                for (int j = 1; j < i; j += 2) {
                    x += dp[j - 1] * dp[i - j - 1];
                    x %= MOD;
                }
                dp[i] = x;
            }
        }
    }
}



class Solution {
    static final int MOD = 1_000_000_007;
    static long[] dp = new long[1001];

    public int numberOfWays(int n) {
        if (n <= 2) {
            return 1;
        } else if (dp[n] > 0) {
            return (int) dp[n];
        }
        long ans = 0L;
        for (int i = 0; i < n; i += 2) {
            ans += (long) numberOfWays(i) * numberOfWays(n - i - 2);
            ans %= MOD;
        }
        return (int) (dp[n] = ans);
    }
}