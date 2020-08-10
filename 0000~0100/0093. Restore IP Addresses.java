/* 
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

A valid IP address consists of exactly four integers (each integer is between 0 and 255) separated by single points.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
 */

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return ans;
        }
        helper(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void helper(String s, int pos, List<String> cur, List<String> ans) {
        if (cur.size() == 4) {
            if (pos == s.length()) ans.add(String.join(".", cur));
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (pos + i > s.length()) break;
            String segment = s.substring(pos, pos + i);
            if (segment.startsWith("0") && segment.length() > 1 
            || (i == 3 && Integer.parseInt(segment) > 255)) continue;
            cur.add(segment);
            helper(s, pos + i, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}


class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        helper(s, 0, "", res);
        return res;
    }
    public void helper(String s, int n, String out, List<String> res) {
        if (n == 4) {
            if (s.isEmpty()) res.add(out);
            return;
        }
        for (int k = 1; k < 4; ++k) {
            if (s.length() < k) break;
            int val = Integer.parseInt(s.substring(0, k));
            if (val > 255 || k != String.valueOf(val).length()) continue;
            helper(s.substring(k), n + 1, out + s.substring(0, k) + (n == 3 ? "" : "."), res);
        }
    }
}


class Solution {

    private List<String> res;
    private StringBuilder sb;
    private char[] chars;

    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        chars = s.toCharArray();
        dfs(0, 0);
        return this.res;
    }
    
    private void dfs(int count, int i) {
        if (count == 4) {
            res.add(sb.toString());
            return;
        } // 终止

        int remainCount = 4 - count;
        int remainsChars = chars.length - i;
        if (remainCount > remainsChars || remainCount * 3 < remainsChars) {
            return;
        } // 剪枝

        int len = sb.length();
        int maxLen = (chars[i] == '0') ? 1 : 3;
        int j = 0;
        if (count == 3) j = chars.length - i - 1;
        for (; j < maxLen && i + j < chars.length; j++) {
            if (j == 2 && isNotValid(chars, i)) continue;
            for (int k = 0; k <= j; k++) sb.append(chars[i + k]);
            if (count < 3) sb.append('.');
            dfs(count + 1, i + j + 1);
            sb.delete(len, count < 3 ? len + j + 2 : len + j + 1);
        }
    }
    
    private boolean isNotValid(char[] chars, int index) {
        int res = (chars[index] - '0') * 100 + (chars[index + 1] - '0') * 10 + chars[index + 2] - '0';
        return res > 255;
    }
}

// ...太难了
    
    private boolean isNotValid(char[] chars, int index) {
        int a = chars[index] - '0';
        if (a > 2) return true;
        else if (a == 1) return false;
        int b = chars[index + 1] - '0', c = chars[index + 2] - '0';
        if (a == 2 && (b * 10 + c) > 55) return true;
        return false;
    }


class Solution {
    private int LEN;
    private List<String> res;
    private StringBuilder sb;
    private char[] chars;

    public List<String> restoreIpAddresses(String s) {
        LEN = s.length();
        res = new ArrayList<>();
        if (s == null || LEN < 4 || LEN > 12) {
            return res;
        }
        sb = new StringBuilder();
        chars = s.toCharArray();
        dfs(4, 0);
        return res;
    }
    
    private void dfs(int count, int i) {
        if (count == 0) {
            res.add(sb.delete(LEN+3, LEN+4).toString());
            return;
        }
        int x = count - 1;
        int y = LEN - i;
        int l = Math.max(y-3*x, 1);
        int r = Math.min(y-x, 3);
        if (chars[i] == '0') r = 1;
        
        int len = sb.length();
        for (int j = l; j <= r; j++) {
            if (j == 3 && isNotValid(chars, i)) return;
            for (int k = 0; k < j; k++) sb.append(chars[i + k]);
            sb.append('.');
            dfs(x, i + j);
            sb.delete(len, len + j + 1);
        }
    }
    
    private boolean isNotValid(char[] c, int i) {
        return ((c[i] - '0') * 100 + (c[i + 1] - '0') * 10 + c[i + 2] - '0') > 255;
    }
}