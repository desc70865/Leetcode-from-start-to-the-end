/* 
On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].

Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.



We may make the following moves:

'U' moves our position up one row, if the position exists on the board;
'D' moves our position down one row, if the position exists on the board;
'L' moves our position left one column, if the position exists on the board;
'R' moves our position right one column, if the position exists on the board;
'!' adds the character board[r][c] at our current position (r, c) to the answer.
(Here, the only positions that exist on the board are positions with letters on them.)

Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.

 

Example 1:

Input: target = "leet"
Output: "DDR!UURRR!!DDD!"
Example 2:

Input: target = "code"
Output: "RR!DDRR!UUL!R!"
 

Constraints:

1 <= target.length <= 100
target consists only of English lowercase letters.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/alphabet-board-path/solution/cou-he-yong-bai-by-keylol-ms9u/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public String alphabetBoardPath(String target) {
        char pre = 'a';
        StringBuilder sb = new StringBuilder();
        for (char c: target.toCharArray()) {
            sb.append(move(pre, c));
            pre = c;
        }
        return sb.toString();
    }

    private StringBuilder move(char start, char end) {
        StringBuilder ans = new StringBuilder();
        if (start != end) {
            int[] a = getPos(start);
            int[] b = getPos(end);
            if (start == 'z') {
                moveEach(ans, 'U', a[0] - b[0]);
                moveEach(ans, 'R', b[1] - a[1]);
            } else {
                moveEach(ans, b[1] >= a[1] ? 'R' : 'L', Math.abs(b[1] - a[1]));
                moveEach(ans, b[0] >= a[0] ? 'D' : 'U', Math.abs(b[0] - a[0]));
            }
        }
        ans.append('!');
        return ans;
    }

    private void moveEach(StringBuilder ans, char dir, int step) {
        while (step-- > 0) {
            ans.append(dir);
        }
    }

    private int[] getPos(char c) {
        int p = c - 'a';
        int row = p / 5;
        int col = p % 5;
        return new int[] {row, col};
    }
}