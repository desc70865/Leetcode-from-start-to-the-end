/* 
Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

 

Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""
 

Note:

A.length == 4
0 <= A[i] <= 9
 */

class Solution {
    int res = -1;
    public String largestTimeFromDigits(int[] A) {
        dfs(A, 0);
        return res < 0 ? "" : print(res / 100, res % 100);
    }
    
    private String print(int hour, int min) {
        StringBuilder sb = new StringBuilder();
        sb.append(hour / 10);
        sb.append(hour % 10);
        sb.append(':');
        sb.append(min / 10);
        sb.append(min % 10);
        return sb.toString();
    }
    
    private void dfs(int[] A, int i) {
        if (i == 4) {
            int k = calc(A);
            if (isValidTime(k)) res = Math.max(res, k);
        }
        for (int j = i; j < 4; j++) {
            swap(A, i, j);
            dfs(A, i+1);
            swap(A, i, j);
        }
    }
    
    private int calc(int[] A) {
        int res = 0;
        for (int num: A) res = res * 10 + num;
        return res;
    }
    
    private boolean isValidTime(int N) {
        return N < 2400 && N % 100 < 60;
    }
    
    private void swap(int[] A, int i, int j) {
        if (i == j) return;
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}