/* 
Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.

Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].

 

Example 1:

Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1
 

Constraints:

1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9
 */

class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[][] cnt = new int[10][10];
        for (int[] d: dominoes) cnt[d[0]][d[1]]++;
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            res += helper(cnt[i][i]);
            for (int j = i + 1; j <= 9; j++) {
                res += helper(cnt[i][j] + cnt[j][i]);
            }
        }
        return res;
    }

    private int helper(int n) {
        return n * (n - 1) / 2;
    }
}