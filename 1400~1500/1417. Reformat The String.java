/* 
Given alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).

You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.

Return the reformatted string or return an empty string if it is impossible to reformat the string.

 

Example 1:

Input: s = "a0b1c2"
Output: "0a1b2c"
Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
Example 2:

Input: s = "leetcode"
Output: ""
Explanation: "leetcode" has only characters so we cannot separate them by digits.
Example 3:

Input: s = "1229857369"
Output: ""
Explanation: "1229857369" has only digits so we cannot separate them by characters.
Example 4:

Input: s = "covid2019"
Output: "c2o0v1i9d"
Example 5:

Input: s = "ab123"
Output: "1a2b3"
 

Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters and/or digits.
 */

class Solution {
    public String reformat(String s) {
        int diff = 0;
        char[] str = s.toCharArray();
        for (char c: str) {
            if (c >= 97) diff++;
            else diff--;
        }
        if (Math.abs(diff) > 1) return "";
        int N = str.length;
        char[] res = new char[N];
        int a = -Math.min(diff, 0), b = 1 - a;
        for (char c:str) {
            if (c >= 97) { res[a] = c; a += 2; }
            else { res[b] = c; b += 2; }
        }
        return new String(res);
    }
}



class Solution {
    public String reformat(String s) {
        int diff = 0;
        char[] str = s.toCharArray();
        for (char c: str) {
            if (c >= 97) diff++;
            else diff--;
        }
        if (Math.abs(diff) > 1) return "";
        int N = str.length;
        int a = -Math.min(diff, 0), b = 1 - a;
        while (a < N && b < N) {
            while (a < N && str[a] >= 97) a += 2;
            if (a >= N) break;
            while (b < N && str[b] < 97) b += 2;
            swap(str, a, b);
            a += 2;
            b += 2;
        }
        return new String(str);
    }

    private void swap(char[] A, int i, int j) {
        char c = A[i];
        A[i] = A[j];
        A[j] = c;
    }
}