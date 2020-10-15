/* 
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 */

class Solution {
    public static int x[]={1,0,-1,0};
    public static int y[]={0,1,0,-1};
    public static Map<Board,Board> map=new HashMap<>();
    static {
        Queue<Board> queue=new ArrayDeque<>();
        queue.add(new Board());
        bfs(queue,map);
    }
    public int slidingPuzzle(int[][] board) {
       Board board1;
        if((board1=(Board)map.get(new Board(board)))!=null){
            return board1.count;
        }else{
            return -1;
        }
    }
    public static void bfs(Queue<Board> queue,Map<Board,Board> map){
        while (!queue.isEmpty()){
            Board board=queue.poll();
            for(int i=0;i<4;i++){
                Board temp=new Board(board);
                temp.move(i);
                if(!map.containsKey(temp)){
                    map.put(temp,temp);
                    queue.add(temp);
                }
            }
        }
    }
    static class Board{
        public int [][]board={{1,2,3},{4,5,0}};
        public int i=1, j=2;
        public int count=0;
        public Board(){}
        public Board(int board[][]){
            this.board=board;
        }
        public Board(Board board){
            this.i=board.i;
            this.j=board.j;
            this.board=new int[2][3];
            for(int i=0;i<this.board.length;i++){
                for(int j=0;j<this.board[0].length;j++){
                    this.board[i][j]=board.board[i][j];
                }
            }
            this.count=board.count;
        }
        public void move(int i){
            if(check(this.i+x[i],this.j+y[i])){
                this.board[this.i][this.j]=this.board[this.i+x[i]][this.j+y[i]];
                this.i+=x[i];
                this.j+=y[i];
                this.board[this.i][this.j]=0;
                this.count++;
            }
        }
        public boolean check(int x,int y){
            return x>=0&&x<=1&&y>=0&&y<=2;
        }
        public int hashCode(){
            return this.board[0][0];
        }
        public boolean equals(Object object){
            Board temp=(Board)object;
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[0].length;j++){
                    if(board[i][j]!=temp.board[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

// 下次一定

class Solution {
    public int slidingPuzzle(int[][] board) {
        int[][] dirs = new int[][] { {1, 3}, {0, 4, 2}, {1, 5}, {0, 4}, {3, 1, 5}, {4, 2} };
        int[] map = new int[7];
        int idx = -1;
        for (int i = 0; i < 6; i++) {
            map[i] = board[i / 3][i % 3];
            if (map[i] == 0) map[6] = i;
        }
        String target = "1,2,3,4,5,0,5";
        Deque<int[]> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        set.add(Arrays.toString(map));
        q.offerLast(map);
        int step = 0;
        while (! q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.pollFirst();
                System.out.println(Arrays.toString(cur));
                int curIdx = cur[6];
                if (curIdx == 5 && target.equals(Arrays.toString(cur))) return step;
                for (int d: dirs[curIdx]) {
                    swap(cur, curIdx, d);
                    cur[6] = d;
                    System.out.println(Arrays.toString(cur));
                    if (set.add(Arrays.toString(cur))) q.offerLast(cur);
                    swap(cur, curIdx, d);
                    cur[6] = curIdx;
                }
            }
            step++;
        }
        return -1;
    }

    private void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}