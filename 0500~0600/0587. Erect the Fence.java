/* 
You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter.

 

Example 1:


Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
Example 2:


Input: points = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]
 

Constraints:

1 <= points.length <= 3000
points[i].length == 2
0 <= xi, yi <= 100
All the given points are unique.
 */

class Solution {
    public int[][] outerTrees(int[][] trees) {
        Arrays.sort(trees, (a, b) -> b[0] - a[0] == 0 ? b[1] - a[1] : b[0] - a[0]);
        int n = trees.length;
        int[] stack = new int[n << 1];
        int top = -1;
        for (int i = 0; i < n; ++i) {
            while (top >= 1 && counterclockwise(trees[stack[top - 1]], trees[stack[top]], trees[i])) {
                --top;
            }
            stack[++top] = i;
        }
        --top;
        for (int i = n - 1; i >= 0; --i) {
            while(top >= 1 && counterclockwise(trees[stack[top - 1]], trees[stack[top]], trees[i])) {
                --top;
            }
            stack[++top] = i;
        }
        boolean[] map = new boolean[n];
        int[][] cache = new int[n][2];
        int cnt = 0;
        for (int i = 0; i <= top; ++i) {
            if (! map[stack[i]]) {
                map[stack[i]] = true;
                cache[cnt++] = trees[stack[i]];
            }
        }
        int[][] ans = new int[cnt][2];
        System.arraycopy(cache, 0, ans, 0, cnt);
        return ans;
    }

    public static boolean counterclockwise(int[] p, int[] q, int[] r) {
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]) > 0;
    }
}