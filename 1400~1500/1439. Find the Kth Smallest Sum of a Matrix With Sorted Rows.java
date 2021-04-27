/* 
You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.

You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.

 

Example 1:

Input: mat = [[1,3,11],[2,4,6]], k = 5
Output: 7
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.  
Example 2:

Input: mat = [[1,3,11],[2,4,6]], k = 9
Output: 17
Example 3:

Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
Output: 9
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.  
Example 4:

Input: mat = [[1,1,10],[2,2,9]], k = 7
Output: 12
 

Constraints:

m == mat.length
n == mat.length[i]
1 <= m, n <= 40
1 <= k <= min(200, n ^ m)
1 <= mat[i][j] <= 5000
mat[i] is a non decreasing array.


You are given an m * n matrix, mat, and an integer k, which has its rows sorted in non-decreasing order.

You are allowed to choose exactly 1 element from each row to form an array. Return the Kth smallest array sum among all possible arrays.

 

Example 1:

Input: mat = [[1,3,11],[2,4,6]], k = 5
Output: 7
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.  
Example 2:

Input: mat = [[1,3,11],[2,4,6]], k = 9
Output: 17
Example 3:

Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
Output: 9
Explanation: Choosing one element from each row, the first k smallest sum are:
[1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.  
Example 4:

Input: mat = [[1,1,10],[2,2,9]], k = 7
Output: 12
 

Constraints:

m == mat.length
n == mat.length[i]
1 <= m, n <= 40
1 <= k <= min(200, n ^ m)
1 <= mat[i][j] <= 5000
mat[i] is a non decreasing array.
 */

class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> a.sum - b.sum);
        Set<String> set = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < m; i++) sum += mat[i][0];
        Item seed = new Item(m, sum);
        pq.offer(seed);
        set.add(Arrays.toString(seed.index));
        while (--k > 0 && pq.size() > 0) {
            Item cur = pq.poll();
            for (int i = 0; i < m; i++) {
                int col = cur.index[i];
                if (col == n - 1) continue;
                Item next = new Item(cur, i, mat[i][col + 1] - mat[i][col]);
                if (set.add(Arrays.toString(next.index))) {
                    pq.offer(next);
                    // pq.forEach(x -> System.out.println(Arrays.toString(x.index)));
                }
            }
        }
        return pq.peek().sum;
    }

    class Item {
        int[] index;
        int sum;

        public Item(int m, int sum) {
            this.index = new int[m];
            this.sum = sum;
        }

        public Item(Item cur, int c, int d) {
            this.index = cur.index.clone();
            index[c]++;
            this.sum = cur.sum + d;
        }
    }
}



class Solution {
    int[][] mat;
    int m, n, k;

    public int kthSmallest(int[][] mat, int k) {
        this.mat = mat;
        this.m = mat.length;
        this.n = mat[0].length;
        this.k = k;
        int L = 0, R = 0;
        for (int i = 0; i < m; i++) {
            L += mat[i][0];
            R += mat[i][n - 1];
        }
        int lowBoundary = L;
        while (L < R) {
            int M = L + R >> 1;
            // min case: only lowBoundary -> 1
            if (dfs(M, 0, lowBoundary, 1) < k) L = M + 1;
            else R = M;
        }
        return L;
    }

    private int dfs(int threshold, int row, int sum, int counter) {
        if (sum > threshold || row == m || counter > k) return counter;
        for (int col = 0; col < n; col++) {
            if (sum + mat[row][col] - mat[row][0] <= threshold) {
                if (col > 0) counter++;
                counter = dfs(threshold, row + 1, sum + mat[row][col] - mat[row][0], counter);
            } else {
                break;
            }
        }
        return counter;
    }
}