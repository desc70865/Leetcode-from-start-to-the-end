/* 
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example:

Input: s = "++++"
Output: true 
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
Follow up:
Derive your algorithm's runtime complexity.
 */

class Solution {
    static Integer[] SG;
    static int SIZE = 25;

    static {
        SG = new Integer[SIZE];
        SG(SIZE - 1);
    }

    public boolean canWin(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int ans = 0;
        int plus = 0;
        for (int i = 0; i < len; i++) {
            if (chs[i] == '+') {
                plus++;
            } else {
                if (plus > 1) {
                    ans ^= SG[plus];
                }
                plus = 0;
            }
        }
        if (plus > 0) {
            ans ^= SG[plus];
        }
        return ans > 0;
    }

    private static int SG(int x) {
        if (SG[x] != null) {
            return SG[x];
        }
        if (x <= 3) {
            return SG[x] = x / 2;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= x / 2 - 1; i++) {
            set.add(SG(i) ^ SG(x - i - 2));
        }
        for (int i = 0; i < SIZE; i++) {
            if (! set.contains(i)) {
                return SG[x] = i;
            }
        }
        return -1;
    }
}