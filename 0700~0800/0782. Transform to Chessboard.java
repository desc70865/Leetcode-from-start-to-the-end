/* 
An N x N board contains only 0s and 1s. In each move, you can swap any 2 rows with each other, or any 2 columns with each other.

What is the minimum number of moves to transform the board into a "chessboard" - a board where no 0s and no 1s are 4-directionally adjacent? If the task is impossible, return -1.

Examples:
Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
Output: 2
Explanation:
One potential sequence of moves is shown below, from left to right:

0110     1010     1010
0110 --> 1010 --> 0101
1001     0101     1010
1001     0101     0101

The first move swaps the first and second column.
The second move swaps the second and third row.


Input: board = [[0, 1], [1, 0]]
Output: 0
Explanation:
Also note that the board with 0 in the top left corner,
01
10

is also a valid chessboard.

Input: board = [[1, 0], [1, 0]]
Output: -1
Explanation:
No matter what sequence of moves you make, you cannot end with a valid chessboard.
Note:

board will have the same number of rows and columns, a number in the range [2, 30].
board[i][j] will be only 0s or 1s.
 */

class Solution {
    int res = 0;

    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int[] row = new int[n];
        int[] col = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                row[i] <<= 1;
                row[i] |= board[i][j];
                col[j] <<= 1;
                col[j] |= board[i][j];
            }
        }
        if (h(row) || h(col)) {
            return -1;
        }
        return res;
    }

    private boolean h(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        int cnt = 0, ans = 0;
        for (int x = nums[0], i = 0; i < n; ++i) {
            set.add(nums[i]);
            if (nums[i] == x) {
                ++cnt;
                if (i % 2 == 0) {
                    ++ans;
                }
            }
        }
        if (set.size() != 2 || Math.abs(n - cnt - cnt) > 1) {
            return true;
        }
        if (n % 2 == 0) {
            res += Math.min(ans, cnt - ans);
        } else {
            if (cnt > n / 2) {
                res += n / 2 + 1 - ans;
            } else {
                res += ans;
            }
        }
        return false;
    }
}