/* 
Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 

Example 1:


Input: grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 6
Explanation: Given three friends living at (0,0), (0,4), and (2,2).
The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
So return 6.
Example 2:

Input: grid = [[1,1]]
Output: 1
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] is either 0 or 1.
There will be at least two friends in the grid.
 */

class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                row.add(i);
                col.add(j);
            }
        }
        return calc(row) + calc(col);
    }

    private int calc(List<Integer> list) {
        Collections.sort(list);
        int ans = 0;
        for (int l = 0, r = list.size() - 1; l < r;) {
            ans += list.get(r--) - list.get(l++);
        }
        return ans;
    }
}



class Solution {
    public int minTotalDistance(int[][] grid) {
        int size = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) size++;
            }
        }
        int[] row = new int[size];
        for (int i = 0, k = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) row[k++] = i;
            }
        }
        int[] col = new int[size];
        for (int j = 0, k = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) col[k++] = j;
            }
        }
        return calc(row) + calc(col);
    }

    private int calc(int[] list) {
        int ans = 0;
        for (int l = 0, r = list.length - 1; l < r;) {
            ans += list[r--] - list[l++];
        }
        return ans;
    }
}