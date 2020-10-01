/* 
Given a m * n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

 

Example 1:

Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column
Example 2:

Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
Example 3:

Input: matrix = [[7,8],[1,2]]
Output: [7]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 10^5.
All elements in the matrix are distinct.
 */

class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> res = new ArrayList();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            int min = matrix[i][0];
            int p = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    p = j;
                }
            }
            int k = -1;
            while (++k < m) if (matrix[k][p] > min) break;
            if (k == m) res.add(min);
        }
        return res;
    }
}