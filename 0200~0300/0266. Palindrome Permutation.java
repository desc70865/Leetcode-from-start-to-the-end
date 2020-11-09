/* 
Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true
 */

class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        for (char c: s.toCharArray()) {
            map[c]++;
        }
        int sum = 0;
        for (int num: map) {
            sum += num % 2;
        }
        return sum <= 1;
    }
}