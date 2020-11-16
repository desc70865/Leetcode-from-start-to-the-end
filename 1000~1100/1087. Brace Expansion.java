/* 
A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.

 

Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]
 

Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
 */

class Solution {
    List<String> res;

    public String[] expand(String S) {
        List<List<Character>> dict = new ArrayList<>();
        char[] chs = S.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] != '{') dict.add(Arrays.asList(chs[i]));
            else {
                List<Character> tmp = new ArrayList<>();
                while (chs[i] != '}') {
                    tmp.add(chs[++i]);
                    i++;
                }
                Collections.sort(tmp);
                dict.add(tmp);
            }
        }
        res = new ArrayList<>();
        dfs(dict, 0, new char[dict.size()]);
        return res.toArray(new String[0]);
    }

    private void dfs(List<List<Character>> dict, int idx, char[] chs) {
        if (idx == dict.size()) {
            res.add(new String(chs));
            return;
        }
        for (char c: dict.get(idx)) {
            chs[idx] = c;
            dfs(dict, idx + 1, chs);
        }
    }
}