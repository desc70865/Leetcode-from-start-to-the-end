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

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget/solution/yi-jing-sha-feng-liao-by-keylol-qrst/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int[] cost = new int[len];
        for (int i = 0; i < len; i++) {
            cost[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int max = 0;
        int sum = 0;
        for (int l = 0, r = 0; r < len; r++) {
            sum += cost[r];
            while (sum > maxCost) {
                sum -= cost[l++];
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
    }
}



class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int l = 0, r = 0;
        for (int sum = 0; r < s.length();) {
            sum += diff(s, t, r++);
            if (sum > maxCost) {
                sum -= diff(s, t, l++);
            }
        }
        return r - l;
    }

    private int diff(String s, String t, int idx) {
        return Math.abs(s.charAt(idx) - t.charAt(idx));
    }
}


/* 
int equalSubstring(char * s, char * t, int maxCost)
{
    int l = 0, r = 0;
    while (s[r] != '\0') if ((maxCost -= fabs(s[r] - t[r++])) < 0) maxCost += fabs(s[l] - t[l++]);
    return r - l;
}
 */


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
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            deque.offerLast(Math.abs(s.charAt(i) - t.charAt(i)) + deque.peekLast());
            if (deque.peekLast() - deque.peekFirst() > maxCost) deque.pollFirst();
        }
        return deque.size() - 1;
    }
}