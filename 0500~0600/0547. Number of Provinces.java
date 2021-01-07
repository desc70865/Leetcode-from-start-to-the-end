/* 
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 

Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */

class Solution {
    int[] p;

    public int findCircleNum(int[][] M) {
        int len = M.length;
        p = new int[len];
        Arrays.fill(p, -1);

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (M[i][j] == 0) continue;
                union(i, j);
            }
        }
        int cnt = 0;
        for (int num: p) if (num == -1) cnt++;
        return cnt;
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            p[a] = b;
        }
    }

    private int find(int x) {
        return p[x] == -1 ? x : (p[x] = find(p[x]));
    }
}