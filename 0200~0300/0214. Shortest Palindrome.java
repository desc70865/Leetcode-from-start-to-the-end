/* 
Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: "abcd"
Output: "dcbabcd"
 */

class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        char[] sNew = new char[n + 1 + n];
        for (int i = 0; i < n; i++) {
            sNew[i] = arr[i];
        }
        sNew[n] = '!';
        for (int i = n - 1; i >= 0; i--) {
            sNew[2 * n - i] = arr[i];
        }

        int nNew = sNew.length;
        int[] f = new int[nNew];
        for (int i = 1; i < nNew; i++) {
            int t = f[i - 1];
            while (t > 0 && sNew[i] != sNew[t]) {
                t = f[t - 1];
            }
            if (sNew[i] == sNew[t]) {
                t++;
            }
            f[i] = t;
        }
        return new String(sNew, n + 1, n - f[nNew - 1]) + s;
    }
}

// KMP +1