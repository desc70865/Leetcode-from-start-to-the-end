/* 
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]

Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.

You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.

Return a list of boolean, the answers to the given queries.

Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.

 

Example 1:


Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
Example 2:

Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites and each course is independent.
Example 3:


Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
Example 4:

Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
Output: [false,true]
Example 5:

Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
Output: [true,false,true,false]
 

Constraints:

2 <= n <= 100
0 <= prerequisite.length <= (n * (n - 1) / 2)
0 <= prerequisite[i][0], prerequisite[i][1] < n
prerequisite[i][0] != prerequisite[i][1]
The prerequisites graph has no cycles.
The prerequisites graph has no repeated edges.
1 <= queries.length <= 10^4
queries[i][0] != queries[i][1]
 */

class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] map = new boolean[n][n];
        for (int[] a: prerequisites) {
            map[a[1]][a[0]] = true;
        }
        
        // Floyd
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] b: queries) {
            res.add(map[b[1]][b[0]]);
        }
        return res;
    }
}

// 拓扑排序:
// 逐个移除入度为0的节点

class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        int[] indegree = new int[n];
        boolean[][] map = new boolean[n][n];
        for (int i = 0; i < n; i++) map[i][i] = true;
        List<List<Integer>> edges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) edges.add(new ArrayList<>());
        Deque<Integer> q = new LinkedList<>();
        for (int[] x: prerequisites) {
            indegree[x[1]]++;
            edges.get(x[0]).add(x[1]);
        }
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        while (! q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                for (Integer x: edges.get(cur)) {
                    indegree[x]--;
                    for (int i = 0; i < n; i++) {
                        if (map[i][cur]) {
                            map[i][x] = true;
                        }
                    }
                    if (indegree[x] == 0) {
                        q.offer(x);
                    }
                }
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] b: queries) {
            res.add(map[b[0]][b[1]]);
        }
        return res;
    }
}



class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        Dot[] dots = new Dot[n];
        for (int i = 0; i < n; i++) {
            dots[i] = new Dot(i);
        }
        for (int[] x: prerequisites) {
            dots[x[0]].add(x[1]);
        }
        List<Boolean> res = new ArrayList<Boolean>();
        for (int[] x: queries) {
            res.add(dots[x[0]].check(dots, x[1]));
        }
        return res;
    }

    private class Dot{
        int id;
        long sign1, sign2;
        boolean updated;

        Dot(int id) {
            this.id = id;
        }

        void add(int id) {
            if (id < 64) sign1 |= 1L << id;
            else sign2 |= 1L << (id - 64);
        }

        void update(Dot[] dots) {
            if (this.updated) {
                return;
            }
            this.updated = true;
            long s1 = sign1, s2 = sign2;
            update(dots, s1, 0);
            update(dots, s2, 64);
        }

        void update(Dot[] dots, long sign, int id) {
            while(sign != 0) {
                if ((sign & 1L) != 0) {
                    Dot d = dots[id];
                    d.update(dots);
                    sign1 |= d.sign1;
                    sign2 |= d.sign2;
                }
                sign >>>= 1;
                id++;
            }
        }
        
        boolean check(Dot[] dots, int id) {
            update(dots);
            return id < 64 ? ((1L << id) & sign1) != 0 : ((1L << (id - 64)) & sign2) != 0;
        }
    }
}