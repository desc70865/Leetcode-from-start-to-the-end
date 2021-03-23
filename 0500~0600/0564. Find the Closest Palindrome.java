/* 
Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.
 */

class Solution {
    public String nearestPalindromic(String n) {
        char[] chs = n.toCharArray();
        for (int l = 0, r = chs.length - 1; l < r; l++, r--) {
            chs[r] = chs[l];
        }
        String curr = String.valueOf(chs);
        String prev = helper(curr, false);
        String rear = helper(curr, true);
        long origin = Long.valueOf(n);
        long c = Long.valueOf(curr);
        long p = Long.valueOf(prev);
        long r = Long.valueOf(rear);
        long d1 = Math.abs(origin - c);
        long d2 = Math.abs(origin - p);
        long d3 = Math.abs(origin - r); 
        if (origin == c) {
            return d2 <= d3 ? prev : rear;
        } else if (origin < c) {
            return d1 < d2 ? curr : prev;
        } else {
            return d1 <= d3 ? curr : rear;
        }
    }

    private String helper(String base, boolean order) {
        int rightSize = base.length() >> 1;
        int leftSize = base.length() - rightSize;
        int left = Integer.valueOf(base.substring(0, leftSize));
        if (order) left++;
        else left--;
        if (left == 0) return rightSize == 0 ? "0" : "9";
        StringBuilder ll = new StringBuilder(String.valueOf(left));
        StringBuilder rr = new StringBuilder(ll).reverse();
        if (rightSize > ll.length()) rr.append(9);
        return ll.append(rr.substring(rr.length() - rightSize)).toString();
    }
}