/* 
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 

Constraints:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.
 */

class Solution {
    public boolean buddyStrings(String A, String B) {
        int N = A.length();
        if (N != B.length()) return false;
        char[] a = A.toCharArray(), b = B.toCharArray();
        int diff = 0;
        int[] dict = new int[26];
        int[] map = new int[26];
        for (int i = 0; i < N; i++) {
            if (a[i] == b[i]) {
                dict[a[i] - 97]++;
                continue;
            }
            diff++;
            map[a[i] - 97]++;
            map[b[i] - 97]--;
        }
        for (int n: map) if (n != 0) return false;
        if (diff == 2) return true;
        if (diff != 0) return false;
        // System.out.println(Arrays.toString(dict));
        for (int n: dict) {
            if (n >= 2) return true;
        }
        return false;
    }
}