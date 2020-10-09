/* 
You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes the existence of a bidirectional path from garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.

There is no garden that has more than three paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

 

Example 1:

Input: n = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]
Example 2:

Input: n = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]
Example 3:

Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
 

Constraints:

1 <= n <= 104
0 <= paths.length <= 2 * 104
paths[i].length == 2
1 <= xi, yi <= n
xi != yi
No garden has four or more paths coming into or leaving it.
 */

class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        int len = paths.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        if (len == 0) return res;
        for (int[] p: paths) Arrays.sort(p);
        Arrays.sort(paths, new MyComparator());
        // for (int[] p: paths) System.out.println(Arrays.toString(p));
        int k = 0;
        for (int i = 1; i <= n; i++) {
            if (k >= len || i < paths[k][1]) continue;
            boolean[] v = new boolean[4];
            while (k < len && i == paths[k][1]) {
                v[res[paths[k][0] - 1] - 1] = true;
                k++;
            }
            for (int j = 0; j < 4; j++) {
                if (v[j]) continue;
                res[i - 1] += j;
                break;
            }
        }
        return res;
    }

    public class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
        }
    }
}



class Solution {
    int[] x = new int[] {1, 2, 1, 3, 1, 2, 1, 4, 1, 2, 1, 3, 1, 2, 1};
    public int[] gardenNoAdj(int n, int[][] paths) {
        int[] res = new int[n];
        
        if (paths.length == 0) {
            Arrays.fill(res, 1);
            return res;
        }
        
        int[] r = new int[n + 1];
        int[][] aux = new int[n + 1][3];
        
        for (int[] p: paths) {
            int x = p[0], y = p[1];
            int a = Math.min(x, y), b = Math.max(x, y);
            aux[b][r[b]++] = a;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < r[i]; j++) {
                res[i - 1] |= mask(res[aux[i][j] - 1]);
            }
            res[i - 1] = x[res[i - 1]];
        }
        
        return res;
    }
    
    private int mask(int x) {
        return 1 << (x - 1);
    }
}



/* class Solution {
    int[] x = new int[] {1, 2, 1, 3, 1, 2, 1, 4, 1, 2, 1, 3, 1, 2, 1};
    public int[] gardenNoAdj(int n, int[][] paths) {
        int[] res = new int[n];
        
        if (paths.length == 0) {
            Arrays.fill(res, 1);
            return res;
        }
        
        int[] r = new int[n + 1];
        int[][] aux = new int[n + 1][3];
        
        for (int[] p: paths) {
            int x = p[0], y = p[1];
            aux[x][r[x]++] = y;
            aux[y][r[y]++] = x;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < r[i]; j++) {
                int k = aux[i][j];
                if (k > i) continue;
                res[i - 1] |= mask(res[k - 1]);
            }
            res[i - 1] = x[res[i - 1]];
        }
        
        return res;
    }
    
    private int mask(int x) {
        return 1 << (x - 1);
    }
} */