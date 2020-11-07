/* 
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
 

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */

class Solution {
    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        List<String> resList = new ArrayList<>();
        int rows = board.length;
        if (rows == 0) {
            return resList;
        }
        int cols = board[0].length;

        for (String word: words) {
            trie.insert(word);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                exist(board, i, j, trie.root, resList);
            }
        }
        return resList;
    }

    private void exist(char[][] board, int row, int col, TrieNode node, List<String> res) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        char cur = board[row][col];
        if (cur == '.' || node.next[cur - 'a'] == null) { 
            return;
        }
        node = node.next[cur - 'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        char origin = board[row][col];
        board[row][col] = '.';
        for (int[] dir: dirs) {
            exist(board, row + dir[0], col + dir[1], node, res);
        }
        board[row][col] = origin;
    }
}

class TrieNode {
    public TrieNode[] next;
    public String word;

    public TrieNode(){
        word = null;
        next = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        char[] chs = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int n = chs[i] - 'a';
            if (cur.next[n] == null) {
                cur.next[n] = new TrieNode();
            }
            cur = cur.next[n];
        }
        cur.word = word;
    }
}