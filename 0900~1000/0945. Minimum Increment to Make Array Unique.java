/* 
Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.

Return the least number of moves to make every value in A unique.

 

Example 1:

Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].
Example 2:

Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 

Note:

0 <= A.length <= 40000
0 <= A[i] < 40000
 */


class Solution {
    public int minIncrementForUnique(int[] A) {
        if (A.length == 0) return 0;
        UnionFind set = new UnionFind();
        int res = 0;
        for (int num: A) {
            if (set.contains(num)) {
                int next = set.find(num) + 1;
                res += (next - num);
                set.insert(next);
            } else {
                set.insert(num);
            }
        }
        return res;
    }
}

class UnionFind {
    int[] p;

    public UnionFind() {
        p = new int[79999];
        Arrays.fill(p, -1);
    }

    public void insert(int x) {
        p[x] = x;
        if (x > 0 && p[x - 1] != -1) union(x, x - 1);
        if (p[x + 1] != -1) union(x, x + 1);
    }

    public boolean contains(int x) {
        return p[x] != -1;
    }
    
    public int find(int x) {
        return x == p[x] ? x : (p[x] = find(p[x]));
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;
        if (rootX < rootY) p[rootX] = rootY;
        else p[rootY] = rootX;
    }
}



class Solution {
    int[] p;
    public int minIncrementForUnique(int[] A) {
        p = new int[80000];
        Arrays.fill(p, -1);
        int move = 0;
        for (int a: A) {
            move += find(a) - a;
        }
        return move;
    }
    
    private int find(int a) {
        return p[a] == -1 ? (p[a] = a) : (p[a] = find(p[a] + 1));
    }
}



class Solution {
    public int minIncrementForUnique(int[] A) {
        int N = A.length;
        if (N < 2) return 0;
        int max = Integer.MIN_VALUE;
        for (int a: A) max = Math.max(max, a);
        int[] map = new int[max + 1];
        for (int a: A) map[a]++;
        int res = 0;
        for (int i = 0; i < max; i++) {
            if (map[i] > 1) {
                res += map[i] - 1;
                map[i + 1] += map[i] - 1;
            }
        }
        res += map[max] * (map[max] - 1) / 2;
        return res;
    }
}