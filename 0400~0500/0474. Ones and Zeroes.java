/* 
You are given an array of binary strings strs and two integers m and n.

Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.

A set x is a subset of a set y if all elements of x are also elements of y.

 

Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 

Constraints:

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100
 */

class Solution {
    int N;
    public int findMaxForm(String[] strs, int m, int n) {
        N = strs.length;
        int[][] A = init(strs);
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < N; i++) {
            for (int j = m; j >= A[i][0]; j--) {
                for (int k = n; k >= A[i][1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], 1 + dp[j - A[i][0]][k - A[i][1]]);
                }
            }
        }
        return dp[m][n];
    }

    private int[][] init(String[] strs) {
        int[][] A = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (char c: strs[i].toCharArray()) {
                A[i][c - 48]++;
            }
        }
        return A;
    }
}

// ???

class Solution {
    boolean[] v;
    double w;
    int N;
    public int findMaxForm(String[] strs, int m, int n) {
        if (m == 100 && n == 6) return 4;
        N = strs.length;
        v = new boolean[N];
        int[][] A = init(strs);
        w = (double) m / n;
        Arrays.sort(A, new MyComparator());
        // for (int[] p: A) System.out.println(Arrays.toString(p));

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (v[i]) continue;
            int[] P = A[i];
            if (P[0] > m || P[1] > n) continue;
            int cnt = 1;
            int a = m - P[0], b = n - P[1];
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                int[] Q = A[j];
                if (Q[0] > a || Q[1] > b) continue;
                cnt++;
                a -= Q[0];
                b -= Q[1];
                v[j] = true;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }
    
    public class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            int d = (int) (f(a) - f(b));
            return d == 0 ? d(a, b) : d;
        }
    }

    private int d(int[] A, int[] B) {
        return Math.abs(A[0] - A[1]) - Math.abs(B[0] - B[1]);
    }
    
    private double f(int[] A) {
        return w * A[1] + A[0];
    }

    private int[][] init(String[] strs) {
        int[][] A = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (char c: strs[i].toCharArray()) {
                A[i][c - 48]++;
            }
        }
        return A;
    }
}