/* 
This is an interactive problem.

You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.

You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.

 

Example 1:

Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
Output: You guessed the secret word correctly.
Explanation:
master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Example 2:

Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
Output: You guessed the secret word correctly.
 

Constraints:

1 <= wordlist.length <= 100
wordlist[i].length == 6
wordlist[i] consist of lowercase English letters.
All the strings of wordlist are unique.
secret exists in wordlist.
numguesses == 10
 */

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    static final int INF = Integer.MAX_VALUE;
    char[][] s;

    public void findSecretWord(String[] wordlist, Master master) {
        int n = wordlist.length;
        this.s = new char[n][6];
        for (int i = 0; i < n; ++i) {
            s[i] = wordlist[i].toCharArray();
        }
        int[][] m = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                m[i][j] = m[j][i] = calcDistance(i, j);
            }
        }
        boolean[] v = new boolean[n];
        for (int match = -1; match < 6; ) {
            int min = INF;
            int idx = 0;
            for (int i = 0; i < n; ++i) {
                if (v[i]) {
                    continue;
                }
                int[] bucket = new int[7];
                for (int j = 0; j < n; ++j) {
                    if (j != i && !v[j]) {
                        ++bucket[m[i][j]];
                    }
                }
                int memo = max(bucket);
                if (min > memo) {
                    min = memo;
                    idx = i;
                }
            }
            match = master.guess(wordlist[idx]);
            v[idx] = true;
            for (int j = 0; j < n; ++j) {
                if (v[j] || j == idx) {
                    continue;
                }
                if (m[idx][j] != match) {
                    v[j] = true;
                }
            }
        }
    }

    private int max(int[] nums) {
        int max = 0;
        for (int num: nums) {
            max = Math.max(max, num);
        }
        return max;
    }
    
    private int calcDistance(int i, int j) {
        int cnt = 0;
        for (int k = 0; k < 6; ++k) {
            if (s[i][k] == s[j][k]) {
                ++cnt;
            }
        }
        return cnt;
    }
}