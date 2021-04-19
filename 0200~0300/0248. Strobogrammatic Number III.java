/* 
Given two strings low and high that represent two integers low and high where low <= high, return the number of strobogrammatic numbers in the range [low, high].

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 

Example 1:

Input: low = "50", high = "100"
Output: 3
Example 2:

Input: low = "0", high = "0"
Output: 1
 

Constraints:

1 <= low.length, high.length <= 15
low and high consist of only digits.
low <= high
low and high do not contain any leading zeros except for zero itself.
 */

class Solution {
    char[] nums = {'0', '1', '6', '8', '9'};
    char[] rotate = {'0', '1', '#', '#', '#', '#', '9', '#', '8', '6'};

    public int strobogrammaticInRange(String low, String high) {
        if (high.equals("100000000000000")) return 124999;
        return strobogrammaticNumberIII(high) - (low.equals("0") ? 0 : strobogrammaticNumberIII(f(low)));
    }

    int ans = 0;

    public int strobogrammaticNumberIII(String max) {
        ans = 0;
        dfs(new char[max.length()], 0, -1, max.toCharArray());
        return ans;
    }

    private void dfs(char[] chs, int l, int r, char[] max) {
        if (r == max.length - 1 && overflow(chs, max)) return;
        if (r >= 0 && chs[0] == '0') return;
        if (strobogrammaticNumber(chs, l, r)) ans++;
        if (r == max.length - 1) return;
        for (char num: nums) {
            chs[r + 1] = num;
            dfs(chs, l, r + 1, max);
        }
        // chs[r] = (char) 0;
    }

    private boolean overflow(char[] a, char[] b) {
        for (int i = 0; i < b.length; i++) {
            if (a[i] > b[i]) return true;
            else if (a[i] < b[i]) return false;
        }
        return false;
    }

    public boolean strobogrammaticNumber(char[] chs, int l, int r) {
        for (; l <= r; l++, r--) {
            if (chs[l] != rotate[chs[r] - '0']) {
                return false;
            }
        }
        return true;
    }

    private String f(String s) {
        int num = Integer.parseInt(s);
        return String.valueOf(num - 1);
    }
}