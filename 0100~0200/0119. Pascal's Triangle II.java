/* 
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?
 */

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            l.add((int) Math.round(choose(rowIndex, i)));
        }
        return l;
    }

    public double choose(double n, double k) {
        double r = 1;
        for (double i = 0; i < k; i++) {
            r *= (n - i);
            r /= (i + 1);
        }
        return r;
    }
}



class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        int[] dp = new int[rowIndex+1];
        dp[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                dp[j] += dp[j-1];
                if (i == rowIndex) {
                    res.add(dp[j]);
                }
            }
        }
        res.add(dp[0]);
        return res;
    }
}



class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        int[] dp = new int[rowIndex+1];
        dp[0] = 1;
        for (int i=0; i < rowIndex; i++) for (int j = i; j > 0; j--) dp[j] += dp[j-1];
        for (int k = rowIndex; k > 0; k--) res.add(dp[k] + dp[k-1]);
        res.add(dp[0]);
        // Collections.reverse(res);
        return res;
    }
}