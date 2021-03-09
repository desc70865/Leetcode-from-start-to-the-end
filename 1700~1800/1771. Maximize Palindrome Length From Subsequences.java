/* 
You are given two strings, word1 and word2. You want to construct a string in the following manner:

Choose some non-empty subsequence subsequence1 from word1.
Choose some non-empty subsequence subsequence2 from word2.
Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can be constructed, return 0.

A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without changing the order of the remaining characters.

A palindrome is a string that reads the same forward as well as backward.

 

Example 1:

Input: word1 = "cacb", word2 = "cbba"
Output: 5
Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba", which is a palindrome.
Example 2:

Input: word1 = "ab", word2 = "ab"
Output: 3
Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which is a palindrome.
Example 3:

Input: word1 = "aa", word2 = "bb"
Output: 0
Explanation: You cannot construct a palindrome from the described method, so return 0.
 

Constraints:

1 <= word1.length, word2.length <= 1000
word1 and word2 consist of lowercase English letters.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/maximize-palindrome-length-from-subsequences/solution/zui-chang-hui-wen-zi-xu-lie-xian-zhi-tia-g6nx/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int longestPalindrome(String word1, String word2) {
        return longestPalindromeSubseq(word1 + word2, word1.length());
    }

    public int longestPalindromeSubseq(String s, int x) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = len - 1; i >= 0; i--) {
            char cur = chs[i];
            int curMax = 0;
            for (int j = i + 1; j < len; j++) {
                int mem = dp[j];
                if (cur == chs[j]) {
                    dp[j] = curMax + 2;
                    if (i < x && j >= x) {
                        max = Math.max(max, dp[j]);
                    }
                }
                curMax = Math.max(mem, curMax);
            }
        }
        return max;
    }
}