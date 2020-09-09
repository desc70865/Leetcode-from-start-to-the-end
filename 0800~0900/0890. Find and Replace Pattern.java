/* 
You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern. 

You may return the answer in any order.

 

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
 

Note:

1 <= words.length <= 50
1 <= pattern.length = words[i].length <= 20
 */

class Solution {
    char[] t;
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        t = pattern.toCharArray();
        for (String word: words) {
            if (check(word.toCharArray())) res.add(word);
        }
        return res;
    }

    private boolean check(char[] word) {
        char[] p = new char[26];
        for (int i = 0; i < word.length; i++) {
            if (p[t[i] - 'a'] == '\u0000') p[t[i] - 'a'] = word[i];
            else if (p[t[i] - 'a'] != word[i]) return false;
        }
        return valid(p);
    }

    private boolean valid(char[] p) {
        Set<Character> set = new HashSet<>();
        for (char c: p) {
            if (c != '\u0000' && ! set.add(c)) return false;
        }
        return true;
    }
}