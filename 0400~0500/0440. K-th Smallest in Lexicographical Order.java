/* 
Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].

 

Example 1:

Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
Example 2:

Input: n = 1, k = 1
Output: 1
 

Constraints:

1 <= k <= n <= 109
 */

class Solution {
    public int findKthNumber(int n, int k) {
        int x = 1;
        --k;
        while (k > 0) {
            int num = getNode(n, x, x + 1);
            if (num <= k) {
                x++;
                k -= num;
            } else {
                x *= 10;
                k--;
            }
        }
        return x;
    }

    private int getNode(int n , long L, long R) {
        int ans = 0;
        for ( ; L <= n; L *= 10, R *= 10) {
            ans += Math.min(n + 1, R) - L;
        }
        return ans;
    }
}