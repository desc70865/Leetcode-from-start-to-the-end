/* 
Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.

For a given query word, the spell checker handles two categories of spelling mistakes:

Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
In addition, the spell checker operates under the following precedence rules:

When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
When the query matches a word up to capitlization, you should return the first such match in the wordlist.
When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
If the query has no matches in the wordlist, you should return the empty string.
Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].

 

Example 1:

Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 

Note:

1 <= wordlist.length <= 5000
1 <= queries.length <= 5000
1 <= wordlist[i].length <= 7
1 <= queries[i].length <= 7
All strings in wordlist and queries consist only of english letters.
 */

class Solution {
    Set<String> set = new HashSet<>();
    Map<String, String> cap = new HashMap<>();
    Map<String, String> vow = new HashMap<>();

    public String[] spellchecker(String[] wordlist, String[] queries) {
        for (String s: wordlist) {
            set.add(s);
            String t = f(s);
            cap.putIfAbsent(t, s);
            vow.putIfAbsent(g(t), s);
        }
        int len = queries.length;
        String[] ans = new String[len];
        for (int i = 0; i < len; i++) {
            ans[i] = helper(queries[i]);
        }
        return ans;
    }

    private String helper(String q) {
        if (set.contains(q)) {
            return q;
        }
        String p = f(q);
        if (cap.containsKey(p)) {
            return cap.get(p);
        }
        String r = g(p);
        if (vow.containsKey(r)) {
            return vow.get(r);
        }
        return "";
    }

    private String f(String s) {
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] < 'a') {
                chs[i] += 32;
            }
        }
        return new String(chs);
    }

    private String g(String s) {
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (isVowel(chs[i])) {
                chs[i] = '*';
            }
        }
        return new String(chs);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}