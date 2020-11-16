/* 
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
 */

class Solution {
    char[] head = new char[] {'0', '1', '6', '8', '9'};
    char[] tail = new char[] {'0', '1', '9', '8', '6'};
    char[] mid = new char[] {'0', '1', '8'};

    List<String> res;

    public List<String> findStrobogrammatic(int n) {
        res = new LinkedList<>();
        dfs(0, n - 1, n, new char[n]);
        return res;
    }

    private void dfs(int left, int right, int len, char[] chs) {
        if (left == len / 2) {
            if (len % 2 == 1) {
                for (char c: mid) {
                    chs[left] = c;
                    res.add(new String(chs));
                }
            } else {
                res.add(new String(chs));
            }
            return;
        }
        for (int i = left == 0 ? 1 : 0; i < 5; i++) {
            chs[left] = head[i];
            chs[right] = tail[i];
            dfs(left + 1, right - 1, len, chs);
        }
    }
}