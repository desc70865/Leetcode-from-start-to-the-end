/* 
Given a year Y and a month M, return how many days there are in that month.

 

Example 1:

Input: Y = 1992, M = 7
Output: 31
Example 2:

Input: Y = 2000, M = 2
Output: 29
Example 3:

Input: Y = 1900, M = 2
Output: 28
 

Note:

1583 <= Y <= 2100
1 <= M <= 12
 */

class Solution {
    int[] month = new int[] {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int numberOfDays(int Y, int M) {
        if (M != 2) return month[M];
        if (Y % 4 != 0 || Y % 100 == 0 && Y % 400 != 0) return month[M];
        return 29;
    }
}