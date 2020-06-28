/* 
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
 */

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> MaxPQ = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] row : matrix) {
            for (int num : row) {
                if (MaxPQ.size() == k && num > MaxPQ.peek())
                    break;
                MaxPQ.offer(num);
                if (MaxPQ.size() > k)
                    MaxPQ.poll();
            }
        }
        return MaxPQ.poll();
    }
}



class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length - 1;
        int left = matrix[0][0], right = matrix[n][n];
        while(left < right){
            int mid = left + (right - left) / 2;
            int count = countNotMoreThanMid(matrix, mid, n);
            if(count < k)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    private int countNotMoreThanMid(int[][] matrix, int mid, int n){
        int count = 0;
        int x = n, y = 0;
        while (x >= 0 && y <= n) {
            if (matrix[x][y] <= mid) {
                count += x + 1;
                y++;
            } else {
                x--;
            }
        }
        return count;
    }
}
