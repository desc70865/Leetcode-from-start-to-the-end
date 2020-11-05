/* 
Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend in order to collect all apples in the tree starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi] means that exists an edge connecting the vertices fromi and toi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple, otherwise, it does not have any apple.

 

Example 1:



Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
Example 2:



Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
Example 3:

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0
 

Constraints:

1 <= n <= 10^5
edges.length == n-1
edges[i].length == 2
0 <= fromi, toi <= n-1
fromi < toi
hasApple.length == n
 */

class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int[] p = new int[n];
        Arrays.fill(p, -1);
        for (int[] x: edges) {
            if (p[x[1]] == -1) p[x[1]] = x[0];
            else p[x[0]] = x[1];
        }
        boolean[] v = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (! hasApple.get(i)) continue;
            int cur = i;
            while (cur != 0) {
                if (v[cur]) break;
                v[cur] = true;
                res += 2;
                cur = p[cur];
            }
        }
        return res;
    }
}



class Solution {
    boolean[] v;
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            int x = edge[0];
            int y = edge[1];
            tree.get(x).add(y);
            tree.get(y).add(x);
        }
        v = new boolean[n];
        v[0] = true;
        return Math.max(dfs(0, tree, hasApple), 0);
    }

    private int dfs(int idx, List<List<Integer>> tree, List<Boolean> hasApple) {
        int sum = 0;
        for (Integer next: tree.get(idx)) {
            if (v[next]) continue;
            v[next] = true;
            int k = dfs(next, tree, hasApple);
            if (k >= 0) sum += k + 2;
        }
        if (sum == 0 && ! hasApple.get(idx)) sum = -1;
        return sum;
    }
}