/* 
Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the number of balls of color i. 

All the balls will be shuffled uniformly at random, then we will distribute the first n balls to the first box and the remaining n balls to the other box (Please read the explanation of the second example carefully).

Please note that the two boxes are considered different. For example, if we have two balls of colors a and b, and two boxes [] and (), then the distribution [a] (b) is considered different than the distribution [b] (a) (Please read the explanation of the first example carefully).

We want to calculate the probability that the two boxes have the same number of distinct balls.

 

Example 1:

Input: balls = [1,1]
Output: 1.00000
Explanation: Only 2 ways to divide the balls equally:
- A ball of color 1 to box 1 and a ball of color 2 to box 2
- A ball of color 2 to box 1 and a ball of color 1 to box 2
In both ways, the number of distinct colors in each box is equal. The probability is 2/2 = 1
Example 2:

Input: balls = [2,1,1]
Output: 0.66667
Explanation: We have the set of balls [1, 1, 2, 3]
This set of balls will be shuffled randomly and we may have one of the 12 distinct shuffles with equale probability (i.e. 1/12):
[1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
After that we add the first two balls to the first box and the second two balls to the second box.
We can see that 8 of these 12 possible random distributions have the same number of distinct colors of balls in each box.
Probability is 8/12 = 0.66667
Example 3:

Input: balls = [1,2,1,2]
Output: 0.60000
Explanation: The set of balls is [1, 2, 2, 3, 4, 4]. It is hard to display all the 180 possible random shuffles of this set but it is easy to check that 108 of them will have the same number of distinct colors in each box.
Probability = 108 / 180 = 0.6
Example 4:

Input: balls = [3,2,1]
Output: 0.30000
Explanation: The set of balls is [1, 1, 1, 2, 2, 3]. It is hard to display all the 60 possible random shuffles of this set but it is easy to check that 18 of them will have the same number of distinct colors in each box.
Probability = 18 / 60 = 0.3
Example 5:

Input: balls = [6,6,6,6,6,6]
Output: 0.90327
 

Constraints:

1 <= balls.length <= 8
1 <= balls[i] <= 6
sum(balls) is even.
Answers within 10^-5 of the actual value will be accepted as correct.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/solution/zhe-dao-ti-mei-shi-yao-yi-si-wo-jian-yi-bqgmm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    long[][] nCr = new long[49][25];
    int[] left;

    public double getProbability(int[] balls) {
        int sum = 0;
        for (int ball: balls) sum += ball;
        double base = f(sum, sum / 2);

        left = new int[balls.length + 1];
        for (int i = balls.length - 1; i >= 0; i--) {
            left[i] = balls[i] + left[i + 1];
        }

        return dfs(balls, 0, 0, 0) / base;
    }

    private double dfs(int[] balls, int color, int diffSum, int diffColor) {
        if (color == balls.length) {
            return diffSum == 0 && diffColor == 0 ? 1 : 0;
        }
        if (Math.abs(diffSum) > left[color] || Math.abs(diffColor) > balls.length - color) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i <= balls[color]; i++) {
            int next = i == 0 ? -1 : (i < balls[color] ? 0 : 1);
            sum += f(balls[color], i) * dfs(balls, color + 1, diffSum + i - (balls[color] - i), diffColor + next);
        }
        return sum;
    }

    private double f(int n, int r) {
        return (double) nCr(n, r) / ((long) 1 << n);
    }

    private long nCr(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (nCr[n][r] > 0) return nCr[n][r];
        return nCr[n][r] = nCr(n - 1, r) + nCr(n - 1, r - 1);
    }
}



class Solution {
    double[][] nCr = new double[49][25];
    int m, n;

    public double getProbability(int[] balls) {
        int len = balls.length;
        int total = 0;
        for (int ball: balls) total += ball;
        m = len * 2;
        n = total / 2;
        double[][] dp = new double[m + 1][n + 1];
        dp[len][0] = 1;
        int sum = 0;
        for (int ball: balls) {
            sum += ball;
            dp = update(dp, ball, sum);
        }
        return dp[len][n] / nCr(total, n);
    }

    private double[][] update(double[][] dp, int ball, int sum) {
        double[][] next = new double[m + 1][n + 1];
        for (int i = 0; i <= ball; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = Math.max(0, sum - n - i); k <= n - i; k++) {
                    if (dp[j][k] == 0) continue;
                    next[i == 0 ? j - 1 : (i == ball) ? j + 1 : j][i + k] += dp[j][k] * nCr(ball, i);
                }
            }
        }
        return next;
    }

    private double nCr(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (nCr[n][r] > 0) return nCr[n][r];
        return nCr[n][r] = nCr(n - 1, r) + nCr(n - 1, r - 1);
    }
}