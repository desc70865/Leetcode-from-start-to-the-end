/* 
Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]
 */

class Solution {
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while (l < r) {
            int x = l + r >> 1;
            if (check(m, n, x, k)) l = x + 1;
            else r = x;
        }
        return l;
    }
    
    private boolean check(int m, int n, int x, int k) {
        int sum = 0;
        for (int i = 1; i <= m && sum < k; i++) {
            sum += Math.min(x / i, n);
        }
        return sum < k;
    }
}



class Solution {
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while (l < r) {
            int x = l + r >> 1;
            if (count(m, n, x) < k) l = x + 1;
            else r = x;
        }
        return l;
    }
    
    private int count(int m, int n, int threshold) {
        int x = 1, y = n;
        int cnt = 0;
        while (y > 0 && x <= m) {
            if (x * y <= threshold) {
                x++;
                cnt += y;
            } else {
                y--;
            }
        }
        return cnt;
    }
}