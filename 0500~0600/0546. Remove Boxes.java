/* 
Given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

 

Example 1:

Input: boxes = [1,3,2,2,2,3,4,3,1]
Output: 23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)
 

Constraints:

1 <= boxes.length <= 100
1 <= boxes[i] <= 100
 */

class Solution {
    public int removeBoxes(int[] boxes) {
        
    }
}



class Solution {
    int[][][] dp;
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        dp = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n - 1, 0);
    }
    
    private int removeBoxesSub(int[] A, int i, int j, int k) {
        if (i > j) return 0;
        if (dp[i][j][k] > 0) return dp[i][j][k];
        
        for (; i + 1 <= j && A[i + 1] == A[i]; i++, k++) ;
        
        int s = (k + 1) * (k + 1) + removeBoxesSub(A, i + 1, j, 0);    
        for (int m = i + 1; m <= j; m++) {
            if (A[i] == A[m]) {
                s = Math.max(s, removeBoxesSub(A, i + 1, m - 1, 0) + removeBoxesSub(A, m, j, k + 1));
            }
        }
        return dp[i][j][k] = s;
    }
}