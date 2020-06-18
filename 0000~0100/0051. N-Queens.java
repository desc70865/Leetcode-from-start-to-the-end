/* 
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

https://assets.leetcode.com/uploads/2018/10/12/8-queens.png

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

class Solution {
    static class Position {
        int x, y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; ++i) {
            for(int j = 0;j < n; ++j) {
                board[i][j] = '.';
            }
        }
        Set<Integer> rowSet = new HashSet<>();
        List<Position> positionList = new ArrayList<>();
        List<List<String>> allList = new ArrayList<>();
        
        backtrack(board, n,  rowSet, allList,  positionList, 0);
        return allList;
    }
    
    public void backtrack(char[][] board, int n, Set<Integer> rowSet, List<List<String>> allList, List<Position> positionList, int col) {
        if(col == n) {
            List<String> oneList = new ArrayList<>();
            for(int i = 0; i < n; ++i) {
                oneList.add(new String(board[i]));
            }
            allList.add(oneList);
            return;
        }
        for(int i = 0; i < n; ++i) {
            board[i][col] = 'Q';            
            positionList.add(new Position(i, col));
            
            if(rowSet.add(i)) {
                if(isValid(positionList)) {
                    backtrack(board, n,rowSet, allList, positionList, col+1);                    
                }
                rowSet.remove(i);
            }
            
            board[i][col] = '.';
            positionList.remove(positionList.size()-1);            
        }                
    }
    
    public boolean isValid( List<Position> positionList) {
        Position p2 = positionList.get(positionList.size()-1);
        for(int i = 0; i < positionList.size()-1; ++i) {
            Position p1 = positionList.get(i);
            if(Math.abs(p1.x-p2.x) == Math.abs(p1.y-p2.y)) {
                return false;
            }
        }
        return true;
    }
}

// ...

class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(char[] row : board){
            Arrays.fill(row, '.');
        }
        List<List<String>> result = new ArrayList<>();
        dfs(board, 0, result);
        return result;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> result){
        if(colIndex == board.length){
            result.add(construct(board));
            return;
        }
        //在当前位置能够放置皇后的情况下，向下一列递归。
        for(int rowIndex = 0; rowIndex < board.length; rowIndex++){
            if(validate(board, rowIndex, colIndex)){
                board[rowIndex][colIndex] = 'Q';
                dfs(board, colIndex + 1, result);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    //判断当前位置(rowIndex,colIndex)是否能够放置皇后
    private boolean validate(char[][] board, int rowIndex, int colIndex){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < colIndex; j++){
                if(board[i][j] == 'Q' && (colIndex - j == Math.abs(rowIndex - i) || rowIndex == i)){
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> construct(char[][] board){
        List<String> result = new LinkedList<>();
        for(char[] rows : board){
            result.add(new String(rows));
        }
        return result;
    }
}

// ......

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), ans, n);
        return ans;
    }
    private void backtrack(List<Integer> currentQueen, List<List<String>> ans, int n) {
        // 当前皇后的个数是否等于 n 了，等于的话就加到结果中
        if (currentQueen.size() == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] t = new char[n];
                Arrays.fill(t, '.');
                t[currentQueen.get(i)] = 'Q';
                temp.add(new String(t));
            }
            ans.add(temp);
            return;
        }
        //尝试每一列
        for (int col = 0; col < n; col++) {
            //当前列是否冲突
            if (!currentQueen.contains(col)) {
                //判断对角线是否冲突
                if (isDiagonalAttack(currentQueen, col)) {
                    continue;
                }
                //将当前列的皇后加入
                currentQueen.add(col);
                //去考虑下一行的情况
                backtrack(currentQueen, ans, n);
                //将当前列的皇后移除，去判断下一列
                //进入这一步就是两种情况，下边的行走不通了回到这里或者就是已经拿到了一个解回到这里
                currentQueen.remove(currentQueen.size() - 1);
            }
        }
    }
    private boolean isDiagonalAttack(List<Integer> currentQueen, int i) {
        // TODO Auto-generated method stub
        int current_row = currentQueen.size();
        int current_col = i;
        //判断每一行的皇后的情况
        for (int row = 0; row < currentQueen.size(); row++) {
            //左上角的对角线和右上角的对角线，差要么相等，要么互为相反数，直接写成了绝对值
            if (Math.abs(current_row - row) == Math.abs(current_col - currentQueen.get(row))) {
                return true;
            }
        }
        return false;
    }
}

// ... to be continue

class Solution {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        helper(0, new ArrayList<Integer>(Collections.nCopies(n, -1)), res);
        return res;
    }
    private static void helper(int curRow, List<Integer> queenCol, List<List<String>> res) {
        int n = queenCol.size();
        if (curRow == n) {
            List<String> out = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] t = new char[n];
                Arrays.fill(t, '.');
                t[queenCol.get(i)] = 'Q';
                out.add(new String(t));
            }
            res.add(out);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(queenCol, curRow, col)) {
                queenCol.set(curRow, col);
                helper(curRow + 1, queenCol, res);
                queenCol.set(curRow, -1);
            }
        }
    }
    private static boolean isValid(List<Integer> queenCol, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == queenCol.get(i) || Math.abs(row - i) == Math.abs(col - queenCol.get(i)))
                return false;
        }
        return true;
    }
}

// ...

class Solution {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        helper(0, new ArrayList<Integer>(Collections.nCopies(n, -1)), res);
        return res;
    }
    private static void helper(int curRow, List<Integer> queenCol, List<List<String>> res) {
        int n = queenCol.size();
        if (curRow == n) {
            List<String> out = new ArrayList<String>(Collections.nCopies(n*n, ".")); // ???
            for (int i = 0; i < n; i++) {
            	out.set(i*n+queenCol.get(i), "Q");
            }
            res.add(out);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(queenCol, curRow, col)) {
                queenCol.set(curRow, col);
                helper(curRow + 1, queenCol, res);
                queenCol.set(curRow, -1);
            }
        }
    }
    private static boolean isValid(List<Integer> queenCol, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == queenCol.get(i) || Math.abs(row - i) == Math.abs(col - queenCol.get(i)))
                return false;
        }
        return true;
    }
}

// 算了算了