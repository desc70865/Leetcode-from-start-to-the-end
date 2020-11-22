/* 
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
 

Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
 */

class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int N = words.length;
        char[][] str = new char[N][];
        for (int i = 0; i < N; i++) {
            str[i] = words[i].toCharArray();
        }
        int[] dp = new int[N];
        int max = 0;
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (helper(str[j], str[i])) dp[i] = Math.max(dp[i], dp[j] + 1);
                else if (str[j].length < str[i].length - 1) break;
            }
            max = Math.max(max, dp[i]);
        }
        return max + 1;
    }

    private boolean helper(char[] a, char[] b) {
        if (a.length + 1 != b.length) return false;
        int N = a.length;
        int i = 0;
        int j = 0;
        while (i < N && j < N + 1) {
            if (a[i] == b[j++]) i++;
        }
        return i == a.length;
    }
}