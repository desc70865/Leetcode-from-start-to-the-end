/* 
In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

 

Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:

Example 2:

Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:

Example 3:

Input:
[
  "\\/",
  "/\\"
]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
The 2x2 grid is as follows:

Example 4:

Input:
[
  "/\\",
  "\\/"
]
Output: 5
Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
The 2x2 grid is as follows:

Example 5:

Input:
[
  "//",
  "/ "
]
Output: 3
Explanation: The 2x2 grid is as follows:

 

Note:

1 <= grid.length == grid[0].length <= 30
grid[i][j] is either '/', '\', or ' '.
 */

class Solution {
    private int[] parent;
    private int n;

    public int regionsBySlashes(String[] grid) {
        n = grid.length + 1;
        parent = new int[n * n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        // 描边
        for (int i = 0; i < n - 1; i++) {
            union(id(0, i), id(0, i + 1));
            union(id(n - 1, i), id(n - 1, i + 1));
            union(id(i, 0), id(i + 1, 0));
            union(id(i, n - 1), id(i + 1, n -1));
        }
        int ans = 1;
        for (int i = 0; i < n - 1; i++) {
            String str = grid[i];
            for (int j = 0; j < n - 1; j++) {
                char c = str.charAt(j);
                if (c == ' ') {
                    continue;
                } else if (c == '/') {
                    if (!union(id(i, j + 1), id(i + 1, j))) {
                        ans++;
                    }
                } else {
                    if (!union(id(i, j), id(i + 1, j + 1))) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private int id(int r, int c) {
        return r * n + c;
    }

    private int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    private boolean union(int p, int q) {
        int rp = find(p);
        int rq = find(q);
        if (rp == rq) {
            return false;
        }
        parent[rq] = rp;
        return true;
    }
}

// 求环的数量
// 闭合时,两端点的根节点相同