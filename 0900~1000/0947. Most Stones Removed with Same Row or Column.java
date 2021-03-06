/* 
On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0
 

Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000
 */

class Solution {
    public int removeStones(int[][] stones) {
        int len = stones.length;
        if (len <= 1) return 0;
        int max = 0;
        for (int[] stone: stones) {
            max = Math.max(max, stone[0]);
            max = Math.max(max, stone[1]);
        }
        DSU dsu = new DSU(++max * 2);

        for (int[] stone: stones)
            dsu.union(stone[0], stone[1] + max);

        Set<Integer> seen = new HashSet<>();
        for (int[] stone: stones)
            seen.add(dsu.find(stone[0]));

        return len - seen.size();
    }
}

class DSU {
    int[] parent;

    public DSU(int len) {
        parent = new int[len];
        for (int i = 1; i < len; i++)
            parent[i] = i;
    }
    
    public int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}