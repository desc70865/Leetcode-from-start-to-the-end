/* 
Strings s1 and s2 are k-similar (for some non-negative integer k) if we can swap the positions of two letters in s1 exactly k times so that the resulting string equals s2.

Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are k-similar.

 

Example 1:

Input: s1 = "ab", s2 = "ba"
Output: 1
Example 2:

Input: s1 = "abc", s2 = "bca"
Output: 2
Example 3:

Input: s1 = "abac", s2 = "baca"
Output: 2
Example 4:

Input: s1 = "aabc", s2 = "abca"
Output: 2
 

Constraints:

1 <= s1.length <= 20
s2.length == s1.length
s1 and s2 contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}.
s2 is an anagram of s1.
 */

class Solution {
    public int kSimilarity(String s1, String s2) {
        if (s1.equals(s2)) return 0;
        Set<String> set = new HashSet<>();
        set.add(s1);
        Deque<String> queue = new ArrayDeque<>();
        queue.add(s1);
        char[] cht = s2.toCharArray();
        int ans = 0;
        while (! queue.isEmpty()) {
            ans++;
            int size = queue.size();
            while (size-- > 0) {
                char[] chs = queue.poll().toCharArray();
                int i = 0;
                while (chs[i] == cht[i]) {
                    i++;
                }
                for (int j = i + 1; j < chs.length; j++) {
                    if (chs[j] == cht[j] || chs[i] != cht[j]) continue;
                    String next = swap(chs, i, j);
                    if (next.equals(s2)) {
                        return ans;
                    }
                    if (set.add(next)) {
                        queue.add(next);
                    }
                }
            }
        }
        return ans;
    }
    
    public String swap(char[] chs, int i, int j) {
        char[] copy = chs.clone();
        copy[i] = chs[j];
        copy[j] = chs[i];
        return new String(copy);
    }
}



class Solution {
    private int len;

    public int kSimilarity(String s1, String s2) {
        char[] chs = s1.toCharArray();
        char[] cht = s2.toCharArray();
        this.len = chs.length;
        return preProcess(chs, cht) + backTracking(0, chs, cht);
    }

    private int backTracking(int startIdx, char[] chs, char[] cht) {
        while (startIdx < len && chs[startIdx] == cht[startIdx]) {
            startIdx++;
        }
        if (startIdx == len) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = startIdx + 1; i < len; i++) {
            if (chs[i] == cht[startIdx] && chs[i] != cht[i]) {
                swap(chs, i, startIdx);
                int cur = backTracking(startIdx + 1, chs, cht);
                if (cur < min) {
                    min = cur + 1;
                }
                swap(chs, i, startIdx);
            }
        }
        return min;
    }
    
    private int preProcess(char[] chs, char[] cht) {
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (chs[i] == cht[i]) continue;
            for (int j = i + 1; j < len; j++) {
                if (chs[j] == cht[i] && chs[i] == cht[j]) {
                    swap(chs, i, j);
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}