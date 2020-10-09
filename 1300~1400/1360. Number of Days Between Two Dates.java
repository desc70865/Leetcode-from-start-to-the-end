/* 
Write a program to count the number of days between two dates.

The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.

 

Example 1:

Input: date1 = "2019-06-29", date2 = "2019-06-30"
Output: 1
Example 2:

Input: date1 = "2020-01-15", date2 = "2019-12-31"
Output: 15
 

Constraints:

The given dates are valid dates between the years 1971 and 2100.
 */

class Solution {
    int[] m = new int[] {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    public int daysBetweenDates(String date1, String date2) {
        int a = calc(date1);
        int b = calc(date2);
        return Math.abs(a - b);
    }

    private int calc(String s) {
        String[] date = s.split("-");
        int year = f(date[0]);
        int month = f(date[1]);
        int day = f(date[2]);
        if (month > 2 && isFucked(year)) day++;
        month = m[month - 1];
        year = (year - 1970) * 365 + (year - 1969) / 4;
        return year + month + day;
    }

    private int f(String s) {
        return Integer.valueOf(s);
    }

    private boolean isFucked(int y) {
        return y % 4 == 0 && y < 2100;
    }
}