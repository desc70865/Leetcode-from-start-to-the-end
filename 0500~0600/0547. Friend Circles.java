/* 
There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:

Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
 

Example 2:

Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

 

Constraints:

1 <= N <= 200
M[i][i] == 1
M[i][j] == M[j][i]
 */

class Solution {
    public int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 0) continue;
                uf.union(i, j);
            }
        }
        // System.out.println(Arrays.toString(uf.p));
        int cnt = 0;
        for (int num: uf.p) if (num == -1) cnt++;
        return cnt;
    }
}

class UnionFind {
    int[] p;

    public UnionFind(int n) {
        p = new int[n];
        Arrays.fill(p, -1);
    }

    public void union(int x, int y) {
        int p1 = findP(x);
        int p2 = findP(y);
        if (p1 != p2) p[p1] = p2;
    }

    public int findP(int x) {
        return p[x] == -1 ? x : findP(p[x]);
    }
}