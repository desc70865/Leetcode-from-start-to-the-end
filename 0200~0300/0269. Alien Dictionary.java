/* 
There is a new alien language that uses the English alphabet. However, the order among letters are unknown to you.

You are given a list of strings words from the dictionary, where words are sorted lexicographically by the rules of this new language.

Derive the order of letters in this language, and return it. If the given input is invalid, return "". If there are multiple valid solutions, return any of them.

 

Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
 */

class Solution {
    Map<Character, Set<Character>> map = new HashMap<>();

    public String alienOrder(String[] words) {
        int len = words.length;
        char[][] chs = new char[len][];
        for (int i = 0; i < len; i++) {
            chs[i] = words[i].toCharArray();
        }
        for (int i = 0; i < len - 1; i++) {
            if (getDifference(chs[i], chs[i + 1])) {
                return "";
            }
        }
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        for (char[] s: chs) {
            for (char c: s) {
                indegree[c - 'a'] = 0;
            }
        }
        for (char k: map.keySet()) {
            for (char c: map.get(k)) {
                indegree[c - 'a']++;
            }
        }
        StringBuilder sb = new StringBuilder();
        Deque<Character> queue = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (indegree[i] != -1) cnt++;
            if (indegree[i] == 0) {
                queue.offer((char) ('a' + i));
            }
        }
        while (queue.size() > 0) {
            char cur = queue.poll();
            sb.append(cur);
            if (map.containsKey(cur)) {
                for (char c: map.get(cur)) {
                    if (--indegree[c - 'a'] == 0) {
                        queue.offer(c);
                    }
                }
            }
        }
        return sb.length() == cnt ? sb.toString() : "";
    }

    private boolean getDifference(char[] a, char[] b) {
        int i = 0;
        for (; i < a.length && i < b.length; i++) {
            if (a[i] == b[i]) continue;
            map.computeIfAbsent(a[i], z -> new HashSet<>()).add(b[i]);
            return false;
        }
        return a.length > b.length;
    }
}



class Solution {
    ListNode[] list = new ListNode[26];
    int index = 26;
    char[] ans = new char[26];
    Boolean[] v = new Boolean[26];

    public String alienOrder(String[] words) {
        int len = words.length;
        char[][] chs = new char[len][];
        int bitmap = 0;
        for (int i = 0; i < len; i++) {
            for (char c: chs[i] = words[i].toCharArray()) {
                bitmap |= 1 << c - 'a';
            }
        }
        for (int i = 0; i < len - 1; i++) {
            if (connect(chs[i], chs[i + 1])) {
                return "";
            }
        }
        for (int i = 0; i < 26; i++) {
            if (v[i] == null && (bitmap >> i & 1) == 1 && dfs(i)) {
                return "";
            }
        }
        return 26 - index == Integer.bitCount(bitmap) ? new String(ans, index, 26 - index) : "";
    }

    private boolean connect(char[] a, char[] b) {
        int i = 0;
        for (; i < a.length && i < b.length; i++) {
            if (a[i] == b[i]) continue;
            list[a[i] - 'a'] = new ListNode(b[i] - 'a', list[a[i] - 'a']);
            return false;
        }
        return a.length > b.length;
    }

    private boolean dfs(int ch) {
        if (v[ch] != null && v[ch]) {
            return false;
        }
        v[ch] = false;
        for (ListNode cur = list[ch]; cur != null; cur = cur.next) {
            if (v[cur.val] == null && dfs(cur.val)) {
                return true;
            }
            if (! v[cur.val]) {
                return true;
            }
        }
        v[ch] = true;
        ans[--index] = (char) (ch + 'a');
        return false;
    }
}