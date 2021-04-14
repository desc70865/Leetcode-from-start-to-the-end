/* 
Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].

 

Example 1:

Input: [1,17,8]
Output: 2
Explanation: 
[1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: [2,2,2]
Output: 1
 

Note:

1 <= A.length <= 12
0 <= A[i] <= 1e9
 */

class Solution {
    Map<Integer, Integer> map = new HashMap();
    Map<Integer, List<Integer>> graph = new HashMap();

    public int numSquarefulPerms(int[] A) {
        for (int x: A) {
            map.merge(x, 1, Integer::sum);
            graph.computeIfAbsent(x, z -> new ArrayList<>());
        }
        for (int x: map.keySet())
            for (int y: map.keySet()) {
                if (checkPerfectSquare(x + y)) {
                    graph.get(x).add(y);
                }
            }
        int ans = 0;
        for (int x: map.keySet())
            ans += dfs(x, A.length - 1);
        return ans;
    }

    public int dfs(int x, int rem) {
        if (rem == 0) return 1;
        map.merge(x, -1, Integer::sum);
        int ans = 0;
        for (int y: graph.get(x))
            if (map.get(y) != 0) {
                ans += dfs(y, rem - 1);
            }
        map.merge(x, 1, Integer::sum);
        return ans;
    }

    private static boolean checkPerfectSquare(int number) {
        double sqrt = Math.sqrt(number);
        return sqrt - Math.floor(sqrt) == 0;
    }
}



class Solution {
    int N;
    Map<Integer, List<Integer>> graph;
    Integer[][] memo;

    public int numSquarefulPerms(int[] A) {
        N = A.length;
        graph = new HashMap();
        memo = new Integer[N][1 << N];
        for (int i = 0; i < N; i++)
            graph.put(i, new ArrayList());
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++) {
                if (checkPerfectSquare(A[i] + A[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        int ans = 0;
        for (int i = 0; i < N; i++)
            ans += dfs(i, 1 << i);
        Map<Integer, Integer> count = new HashMap();
        for (int x: A)
            ans /= count.merge(x, 1, Integer::sum);
        return ans;
    }

    public int dfs(int node, int mask) {
        if (mask == (1 << N) - 1)
            return 1;
        if (memo[node][mask] != null)
            return memo[node][mask];
        int ans = 0;
        for (int next: graph.get(node))
            if ((mask >> next & 1) == 0)
                ans += dfs(next, mask | 1 << next);
        return memo[node][mask] = ans;
    }

    private static boolean checkPerfectSquare(int number) {
        double sqrt = Math.sqrt(number);
        return sqrt - Math.floor(sqrt) == 0;
    }
}



class Solution {
    int ans = 0;

    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        backTracking(-1, 0, new boolean[A.length], A);
        return ans;
    }

    private void backTracking(int prev, int idx, boolean[] v, int[] A) {
        if (idx == A.length) {
            ans++;
            return;
        }
        for (int i = 0; i < A.length; i++) {
            if (v[i] || i > 0 && A[i] == A[i - 1] && ! v[i - 1]) continue;
            if (prev >= 0 && ! checkPerfectSquare(prev + A[i])) continue;
            v[i] = true;
            backTracking(A[i], idx + 1, v, A);
            v[i] = false;
        }
    }

    private static boolean checkPerfectSquare(int number) {
        int sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }
}