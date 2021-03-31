/* 
Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.

He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.

 

Example 1:

Input: n = 4
Output: 10
Explanation: After the 4th day, the total is 1 + 2 + 3 + 4 = 10.
Example 2:

Input: n = 10
Output: 37
Explanation: After the 10th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2nd Monday, Hercy only puts in $2.
Example 3:

Input: n = 20
Output: 96
Explanation: After the 20th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
 

Constraints:

1 <= n <= 1000
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/solution/huan-xing-by-keylol-l5i6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int totalMoney(int n) {
        return helper(n / 7, n % 7);
    }

    private int helper(int k, int r) {
        return (k + 7) * 7 * k / 2 + k * r + (1 + r) * r / 2;
        // int sum = 0;
        // for (int i = 1; i <= k; i++) {
        //     sum += (i + 3) * 7;
        // }
        // for (int j = 1; j <= r; j++) {
        //     sum += j + k;
        // }
        // return sum;
    }
}



class Solution {
    public int totalMoney(int n) {
        return helper(n / 7, n % 7);
    }

    private int helper(int k, int r) {
        return sum(k, 7) + k * r + sum(r, 1);
    }

    private int sum(int x, int a) {
        return (x + a) * a * x / 2;
    }
}