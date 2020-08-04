/* 
Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.

 

Example 1:

Input: date = "2019-01-09"
Output: 9
Explanation: Given date is the 9th day of the year in 2019.
Example 2:

Input: date = "2019-02-10"
Output: 41
Example 3:

Input: date = "2003-03-01"
Output: 60
Example 4:

Input: date = "2004-03-01"
Output: 61
 

Constraints:

date.length == 10
date[4] == date[7] == '-', and all other date[i]'s are digits
date represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.
 */

class Solution {
    public int dayOfYear(String date) {
        int[] s = new int[10];
        int i = 0, ex = 0, res = 0;
        for (char c: date.toCharArray()) s[i++] = c - '0';
        int year = ((s[0] * 10 + s[1] ) * 10 + s[2]) * 10 + s[3];
        int month = s[5] * 10 + s[6];
        int day = s[8] * 10 + s[9];
        if (year % 400 == 0 || year % 100 != 0 && year % 4 == 0) ex++;
        int[] t = new int[] { 0, 31, 28 + ex, 31, 30, 31, 30, 31, 31, 30, 31, 30 };
        for (i = 0; i < month; i++) res += t[i];
        return res + day;
    }
}