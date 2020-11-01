/* 
Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.

A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

 

Example 1:

Input: n = 1
Output: 5
Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
Example 2:

Input: n = 2
Output: 15
Explanation: The 15 sorted strings that consist of vowels only are
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
Example 3:

Input: n = 33
Output: 66045
 

Constraints:

1 <= n <= 50 
 */

class Solution {
    int res = 0;
    public int countVowelStrings(int n) {
        dfs(n, 0);
        return res;
    }
    
    private void dfs(int n, int idx) {
        if (n == 0) {
            res++;
            return;
        }
        for (int i = idx; i < 5; i++) {
            dfs(n - 1, i);
        }
    }
}



class Solution {
    public int countVowelStrings(int n) {
        return helper(new int[n + 1][6], n, 5);
    }
    
    // @return memo[n][rem]
    private int helper(int memo[][], int n, int rem) {
        if (memo[n][rem] != 0) return memo[n][rem];
        if (n == 1) return rem;
        if (rem == 1) return 1;
        return memo[n][rem] = helper(memo, n - 1, rem) + helper(memo, n, rem - 1);
    }
}