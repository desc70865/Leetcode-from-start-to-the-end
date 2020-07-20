/* 
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

 

Example 1:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:



Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
 

Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
 */

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        
    }
}



class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        ArrayList<double[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new double[] { edges[i][1], succProb[i] });
            graph[edges[i][1]].add(new double[] { edges[i][0], succProb[i] });
        }
        boolean[] visited = new boolean[n];
        
        // 优先队列 ???
        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        queue.add(new double[] { start, 1.0 });
        
        while (!queue.isEmpty()) {
            double[] cur = queue.poll();
            if (visited[(int) cur[0]]) {
                continue;
            }
            if (cur[0] == end) {
                return cur[1];
            }
            visited[(int) cur[0]] = true;
            for (double[] next: graph[(int) cur[0]]) {
                queue.add(new double[] { next[0], cur[1] * next[1] });
            }
        }
        return 0.0;
    }
}



class Solution {
    private ArrayList<double[]>[] graph;
    private boolean[] visited;
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new double[] { edges[i][1], succProb[i] });
            graph[edges[i][1]].add(new double[] { edges[i][0], succProb[i] });
        }
        
        visited = new boolean[n];
        
        PriorityQueue<double[]> startQueue = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        startQueue.add(new double[] { start, 1.0 });
        
        return bfs(startQueue, end);
    }
    
    private double bfs(PriorityQueue<double[]> startQueue, double end) {
        while (!startQueue.isEmpty()) {
            double[] cur = startQueue.poll();
            if (visited[(int) cur[0]]) {
                continue;
            }
            if (cur[0] == end) {
                return cur[1];
            }
            visited[(int) cur[0]] = true;
            for (double[] next: graph[(int) cur[0]]) {
                startQueue.add(new double[] { next[0], cur[1] * next[1] });
            }
        }
        return 0.0;
    }
}



class Solution {
    private double[] ps;
    private int cnt = 1;
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        ps = new double[n];
        Arrays.fill(ps, -1);
        ps[start] = 1;
        
        while (cnt != 0) {
            cnt = 0;
            for (int i = 0; i < edges.length; i++) {
                helper(edges[i][0], edges[i][1], succProb[i]);
                helper(edges[i][1], edges[i][0], succProb[i]);
            }
        }
        return ps[end] < 0.0 ? 0.0 : ps[end];
    }
    
    private void helper(int x, int y, double succProb) {
        if (ps[x] >= 0) {
            double tmp = ps[x] * succProb;
            if (tmp > ps[y] + 0.000000000001) {
                ps[y] = tmp;
                cnt++;
            }
        }
    }
}