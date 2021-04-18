/* 
We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.

 

Example 1:

Input: 20
Output: 6
Explanation: 
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
Example 2:

Input: 100
Output: 19
Explanation: 
The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 

Note:

1 <= N <= 10^9
 */

class Solution {
    int ans = 0;
    char[] nums = {'0', '1', '6', '8', '9'};
    char[] rotate = {'0', '1', '#', '#', '#', '#', '9', '#', '8', '6'};
    char[] max;
    int len = 0;

    public int confusingNumberII(int N) {
        this.max = String.valueOf(N).toCharArray();
        this.len = max.length;
        dfs(new char[len], 0, -1);
        return ans;
    }

    private void dfs(char[] chs, int l, int r) {
        if (r == len - 1 && overflow(chs, max)) return;
        if (r >= 0 && chs[0] == '0') return;
        if (confusingNumber(chs, l, r)) ans++;
        if (r == len - 1) return;
        for (char num: nums) {
            chs[r + 1] = num;
            dfs(chs, l, r + 1);
        }
        // chs[r] = (char) 0;
    }

    private boolean overflow(char[] a, char[] b) {
        for (int i = 0; i < len; i++) {
            if (a[i] > b[i]) return true;
            else if (a[i] < b[i]) return false;
        }
        return false;
    }

    public boolean confusingNumber(char[] chs, int l, int r) {
        for (; l <= r; l++, r--) {
            if (chs[l] != rotate[chs[r] - '0']) {
                return true;
            }
        }
        return false;
    }
}