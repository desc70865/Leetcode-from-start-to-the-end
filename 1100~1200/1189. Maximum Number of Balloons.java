/* 
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

 

Example 1:



Input: text = "nlaebolko"
Output: 1
Example 2:



Input: text = "loonbalxballpoon"
Output: 2
Example 3:

Input: text = "leetcode"
Output: 0
 

Constraints:

1 <= text.length <= 10^4
text consists of lower case English letters only.
 */

class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] A = new int[26];
        for (char c: text.toCharArray()) A[c - 97]++;
        int[] m = new int[] { A[0], A[1], A[11] / 2, A[13], A[14] / 2} ;
        int min = text.length();
        for (int n: m) min = Math.min(min, n);
        return min;
    }
}