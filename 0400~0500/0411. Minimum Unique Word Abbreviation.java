/* 
A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)
Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced substrings are adjacent.

The length of an abbreviation is the number of letters that were not replaced plus the number of substrings that were replaced. For example, the abbreviation "s10n" has a length of 3 (2 letters + 1 substring) and "su3i1u2on" has a length of 9 (6 letters + 3 substrings).

Given a target string target and an array of strings dictionary, return an abbreviation of target with the shortest possible length such that it is not an abbreviation of any string in dictionary. If there are multiple shortest abbreviations, return any of them.

 

Example 1:

Input: target = "apple", dictionary = ["blade"]
Output: "a4"
Explanation: The shortest abbreviation of "apple" is "5", but this is also an abbreviation of "blade".
The next shortest abbreviations are "a4" and "4e". "4e" is an abbreviation of blade while "a4" is not.
Hence, return "a4".
Example 2:

Input: target = "apple", dictionary = ["blade","plain","amber"]
Output: "1p3"
Explanation: "5" is an abbreviation of both "apple" but also every word in the dictionary.
"a4" is an abbreviation of "apple" but also "amber".
"4e" is an abbreviation of "apple" but also "blade".
"1p3", "2p2", and "3l1" are the next shortest abbreviations of "apple".
Since none of them are abbreviations of words in the dictionary, returning any of them is correct.
 

Constraints:

target.length == m
dictionary.length == n
1 <= m <= 21
0 <= n <= 1000
1 <= dictionary[i] <= 100
log2(n) + m <= 21 if n > 0
 */

class Solution {
    StringBuilder sb = new StringBuilder();

    public String minAbbreviation(String target, String[] dictionary) {
        if (dictionary == null || target == null) {
            return "";
        }
        int targetLen = target.length();
        Set<String> list = new HashSet<>();
        for (String word: dictionary) {
            if (word.length() == targetLen) {
                helper(0, 0, word.toCharArray(), list);
            }
        }
        if (list.size() == 0) {
            return "" + targetLen;
        }
        Set<String> aux = new HashSet<>();
        helper(0, 0, target.toCharArray(), aux);
        List<String> ans = new ArrayList<>(aux);
        Collections.sort(ans, (a, b) -> count(a) == count(b) ? a.compareTo(b) : count(a) - count(b));
        for (String word: ans) {
            if (! list.contains(word)) {
                return word;
            }
        }
        return "";
    }

    private void helper(int k, int idx, char[] word, Set<String> set) {
        if (idx == word.length) {
            if (k != 0) {
                sb.append(k);
            }
            set.add(sb.toString());
            return;
        }
        int lenCache = sb.length();
        helper(k + 1, idx + 1, word, set);
        sb.setLength(lenCache);
        if (k != 0) {
            sb.append(k);
        }
        sb.append(word[idx]);
        helper(0, idx + 1, word, set);
        sb.setLength(lenCache);
    }

    private int count(String str) {
        char[] s = str.toCharArray();
        int cnt = 0;
        for (int i = 0; i < s.length;) {
            ++cnt;
            if (Character.isDigit(s[i])) {
                while (i < s.length && Character.isDigit(s[i])) {
                    ++i;
                }
            } else {
                ++cnt;
                ++i;
            }
        }
        return cnt;
    }
}



class Solution {
    public String minAbbreviation(String target, String[] dictionary) {
        if (target == null || target.length() == 0) {
            return "";
        }
        if (dictionary == null || dictionary.length == 0) {
            return String.valueOf(target.length());
        }
        int targetLen = target.length();
        int dicSize = dictionary.length;
        DictionaryPool pool = new DictionaryPool(dicSize);
        for (String word: dictionary) {
            if (word.length() == targetLen) {
                pool.add(getMask(target, word));
            }
        }
        minLen = targetLen;
        ans = (1 << targetLen) - 1;
        dfs(target, 0, 0, 0, pool);
        return decode(target, ans);
    }

    private int minLen;
    private int ans;

    private void dfs(String target, int idx, int path, int curLen, DictionaryPool pool) {
        if (curLen >= minLen) {
            return;
        }
        int len = target.length();
        if (pool.isEmpty()) {
            if (idx < len) {
                ++curLen;
            }
            if (curLen < minLen) {
                minLen = curLen;
                path <<= (len - idx);
                ans = path;
            }
            return;
        }
        if (idx >= len) {
            return;
        }
        for (int i = idx; i < len; ++i) {
            if (curLen == 0|| (path & 1) == 1) {
                dfs(target, i + 1, path << (i + 1 - idx), curLen + 1, pool);
            }
        }
        int i = 0, curSize = pool.size;
        while (i < pool.size) {
            int mask = pool.masks[i];
            int offset = len - 1 - idx;
            if ((mask &(1 << offset)) == 0) {
                pool.remove(i);
            } else {
                ++i;
            }
        }
        dfs(target, idx + 1, (path << 1) | 1, curLen + 1, pool);
        pool.setSize(curSize);
    }

    private int getMask(String target, String s) {
        int mask = 0;
        for (int i = 0; i < target.length(); i++) {
            mask <<= 1;
            if (s.charAt(i) == target.charAt(i)) {
                mask |= 1;
            }
        }
        return mask;
    }

    private String decode(String target, int mask) {
        StringBuilder sb = new StringBuilder();
        int len =  target.length();
        int m = 1 << (len - 1), count = 0;
        for (int i = 0; i < len; ++i, m >>= 1) {
            if ((mask & m) == 0) {
                ++count;
            } else {
                if (count > 0) {
                    sb.append(count);
                }
                count = 0;
                sb.append(target.charAt(i));
            }
        }
        if (count > 0) {
            sb.append(count);
        }
        return sb.toString();
    }

    private class DictionaryPool {
        public int[] masks;
        public int size;

        public DictionaryPool(int n) {
            this.masks = new int[n];
            this.size = 0;
        }

        public void add(int mask) {
            this.masks[size++] = mask;
        }

        public void remove(int idx) {
            int temp = masks[size - 1];
            masks[size - 1] = masks[idx];
            masks[idx] = temp;
            --size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }
}