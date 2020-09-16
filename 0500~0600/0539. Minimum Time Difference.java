/* 
Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
 */

class Solution {
    public int findMinDifference(List<String> timePoints) {
        int N = timePoints.size();
        if (N > 1440) return 0;
        int[] aux = new int[N];
        int idx = 0;
        for (String s: timePoints) {
            aux[idx++] = value(s.toCharArray());
        }
        Arrays.sort(aux);
        int res = 1440 + aux[0] - aux[N- 1];
        for (int i = 1; i < N; i++) res = Math.min(res, aux[i] - aux[i - 1]);
        return res;
    }

    private int value(char[] s) {
        int a = charToInt(s[0]);
        int b = charToInt(s[1]);
        int c = charToInt(s[3]);
        int d = charToInt(s[4]);
        return (a * 10 + b) * 60 + (c * 10 + d);
    }

    private int charToInt(char c) {
        return c - '0';
    }
}