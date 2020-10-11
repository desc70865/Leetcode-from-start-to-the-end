/* 
You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
 */

class Solution {
    public boolean checkRecord(String s) {
        char[] str = s.toCharArray();
        boolean f = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'P') continue;
            else if (str[i] == 'A') {
                if (f) return false;
                f = true;
            }
            else if (i > 1 && str[i - 1] == 'L' && str[i - 2] == 'L') return false;
        }
        return true;
    }
}