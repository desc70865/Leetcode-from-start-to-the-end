/* 
You are given two identical eggs and you have access to a building with n floors labeled from 1 to n.

You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.

In each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.

Return the minimum number of moves that you need to determine with certainty what the value of f is.

 

Example 1:

Input: n = 2
Output: 2
Explanation: We can drop the first egg from floor 1 and the second egg from floor 2.
If the first egg breaks, we know that f = 0.
If the second egg breaks but the first egg didn't, we know that f = 1.
Otherwise, if both eggs survive, we know that f = 2.
Example 2:

Input: n = 100
Output: 14
 

Constraints:

1 <= n <= 1000
 */

class Solution {
    public int twoEggDrop(int n) {
        return superEggDrop(2, n);
    }
    
    public int superEggDrop(int K, int N) {
        int[] dp = new int[K + 1];
        int m = 0;
        for (; dp[K] < N; ++m) {
            for (int i = K; i > 0; --i) {
                dp[i] += dp[i - 1] + 1;
            }
        }
        return m;
    }
}