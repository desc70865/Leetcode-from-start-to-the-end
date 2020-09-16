/* 
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16 
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4 
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0 
Explanation: No such pair of words.
 

Constraints:

0 <= words.length <= 10^3
0 <= words[i].length <= 10^3
words[i] consists only of lowercase English letters.
 */

class Solution {
    public int maxProduct(String[] words) {
        int N = words.length;
        if (N < 2) return 0;
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        int res = 0;
        int[] len = new int[N];
        int[] hash = new int[N];
        len[0] = words[0].length();
        for (int i = 1; i < N; i++) {
            len[i] = words[i].length();
            hash[i] = charToHash(words[i]);
            if (len[i] * len[0] < res) break;
            for (int j = 0; j < i; j++) {
                if (hash[i] ^ hash[j] == 0) res = Math.max(res, len[i] * len[j]);
            }
        }
        return res;
    }

    private boolean check(String a, String b) {
        Set<Character> set = new HashSet<>();
        for (char c: a.toCharArray()) set.add(c);
        for (char c: b.toCharArray()) if (set.contains(c)) return false;
        return true;
    }
}



class Solution {
    public int maxProduct(String[] words) {
        int N = words.length;
        if (N < 2) return 0;
        
        int[] len = new int[N];
        int[] hash = new int[N];
        int idx = 0;

        for (String word: words) {
            len[idx] = word.length();
            hash[idx++] = charToHash(word);
        }
        
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if ((hash[i] & hash[j]) == 0) res = Math.max(res, len[i] * len[j]);
            }
        }
        return res;
    }

    private int charToHash(String a) {
        int res = 0;
        for (char c: a.toCharArray()) res |= (1 << c - 97);
        return res;
    }
}