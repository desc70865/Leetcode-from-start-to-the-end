/* 
Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.

Notice: string "abc" repeated 0 times is "",  repeated 1 time is "abc" and repeated 2 times is "abcabc".

 

Example 1:

Input: a = "abcd", b = "cdabcdab"
Output: 3
Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
Example 2:

Input: a = "a", b = "aa"
Output: 2
Example 3:

Input: a = "a", b = "a"
Output: 1
Example 4:

Input: a = "abc", b = "wxyz"
Output: -1
 

Constraints:

1 <= a.length <= 104
1 <= b.length <= 104
a and b consist of lower-case English letters.
 */

class Solution {
    public int repeatedStringMatch(String a, String b) {
        int k = b.length() / a.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(a);
        }
        for (int i = k; i <= k + 2; i++) {
            if (sb.toString().indexOf(b) >= 0) {
                return i;
            }
            sb.append(a);
        }
        return -1;
    }
}



class Solution {
    public int repeatedStringMatch(String A, String B) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        for (int offset = 0; offset < a.length; offset++) {
            int idx = locate(a, b, offset);
            if (idx > 0) {
                return helper(a.length, b.length, offset);
            } else if (idx + a.length < 0) {
                break;
            }
        }
        return -1;
    }

    private int helper(int a, int b, int c) {
        int x = b - a + c;
        return 1 + x / a + (x % a <= 0 ? 0 : 1);
    }
    
    public int locate(char[] a, char[] b, int offset) {
        int x = offset;
        for (char c: b) {
            if (a[offset++ % a.length] != c) {
                return x - offset;
            }
        }
        return 1;
    }
}