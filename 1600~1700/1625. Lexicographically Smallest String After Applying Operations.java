/* 
You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.

You can apply either of the following two operations any number of times and in any order on s:

Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0. For example, if s = "3456" and a = 5, s becomes "3951".
Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".
Return the lexicographically smallest string you can obtain by applying the above operations any number of times on s.

A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "0158" is lexicographically smaller than "0190" because the first position they differ is at the third letter, and '5' comes before '9'.

 

Example 1:

Input: s = "5525", a = 9, b = 2
Output: "2050"
Explanation: We can apply the following operations:
Start:  "5525"
Rotate: "2555"
Add:    "2454"
Add:    "2353"
Rotate: "5323"
Add:    "5222"
​​​​​​​Add:    "5121"
​​​​​​​Rotate: "2151"
​​​​​​​Add:    "2050"​​​​​​​​​​​​
There is no way to obtain a string that is lexicographically smaller then "2050".
Example 2:

Input: s = "74", a = 5, b = 1
Output: "24"
Explanation: We can apply the following operations:
Start:  "74"
Rotate: "47"
​​​​​​​Add:    "42"
​​​​​​​Rotate: "24"​​​​​​​​​​​​
There is no way to obtain a string that is lexicographically smaller then "24".
Example 3:

Input: s = "0011", a = 4, b = 2
Output: "0011"
Explanation: There are no sequence of operations that will give us a lexicographically smaller string than "0011".
Example 4:

Input: s = "43987654", a = 7, b = 3
Output: "00553311"
 

Constraints:

2 <= s.length <= 100
s.length is even.
s consists of digits from 0 to 9 only.
1 <= a <= 9
1 <= b <= s.length - 1
 */


class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        int startPos = 0;
        char[] minChars = new char[n];
        System.arraycopy(chars, 0, minChars, 0, n);

        for (;;) {
            if ((startPos & 0x1) == 1 || (b & 0x1) == 1) {
                minPos(chars, startPos, a);
            }
            int secondPos = (startPos + 1) % n;
            minPos(chars, secondPos, a);
            if (compareMin(chars, startPos, minChars, 0) < 0) {
                System.arraycopy(chars, startPos, minChars, 0, n - startPos);
                System.arraycopy(chars, 0, minChars, n - startPos, startPos);
            }

            startPos -= b;
            if (startPos < 0) {
                startPos += n;
            }
            if (startPos == 0) {
                break;
            }
        }
        return new String(minChars);
    }

    private void minPos(char[] chars, int startPos, int a) {
        int startNum = chars[startPos] - '0';
        int minStartNum = getMinValue(startNum, a);
        if (minStartNum != startNum) {
            int addDiff = minStartNum + 10 - startNum;
            addVal(startPos, addDiff, chars);
        }
    }

    private int getMinValue(int a, int b) {
        int min = a;
        int tmp = a + b;
        tmp %= 10;

        while (tmp != a) {
            min = Math.min(tmp, min);
            tmp += b;
            tmp %= 10;
        }

        return min;
    }

    private void addVal(int pos, int a, char[] chars) {
        int start = (pos & 0x1) == 1 ? 1 : 0;
        for (int i = start; i < chars.length; i += 2) {
            chars[i] += a;
            if (chars[i] > '9') {
                chars[i] = (char)('0' + chars[i] - '9' - 1);
            }
        }
    }

    private int compareMin(char[] chars1, int pos1, char[] chars2, int pos2) {
        int i = pos1;
        int j = pos2;
        int n = chars1.length;
        while (chars1[i] == chars2[j]) {
            i++;
            j++;
            i %= n;
            j %= n;
            if (i == pos1) {
                break;
            }
        }
        return chars1[i] - chars2[j];
    }
}