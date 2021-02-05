/* 
You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.

You are also given an integer maxCost.

Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of twith a cost less than or equal to maxCost.

If there is no substring from s that can be changed to its corresponding substring from t, return 0.

 

Example 1:

Input: s = "abcd", t = "bcdf", maxCost = 3
Output: 3
Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
Example 2:

Input: s = "abcd", t = "cdef", maxCost = 3
Output: 1
Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.
Example 3:

Input: s = "abcd", t = "acde", maxCost = 0
Output: 1
Explanation: You can't make any change, so the maximum length is 1.
 

Constraints:

1 <= s.length, t.length <= 10^5
0 <= maxCost <= 10^6
s and t only contain lower case English letters.
 */

class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int[] diff = helper(s, t);
        int l = 0, r = 0;
        int sum = 0;
        int max = 0;
        while (r < diff.length) {
            sum += diff[r];
            while (sum > maxCost) {
                sum -= diff[l++];
            }
            if (max < r - l + 1) {
                max = r - l + 1;
            }
            r++;
        }
        return max;
    }

    private int[] helper(String s, String t) {
        int len = s.length();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        return ans;
    }
}



class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int[] cost = new int[len + 1];
        int left = 0;
        for (int i = 0; i < len; i++) {
            cost[i + 1] = Math.abs(s.charAt(i) - t.charAt(i)) + cost[i];
            if (cost[i + 1] - cost[left] > maxCost) left++;
        }
        return len - left;
    }
}



class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            q.offerLast(Math.abs(s.charAt(i) - t.charAt(i)) + q.peekLast());
            if (q.peekLast() - q.peekFirst() > maxCost) q.pollFirst();
        }
        return q.size() - 1;
    }
}