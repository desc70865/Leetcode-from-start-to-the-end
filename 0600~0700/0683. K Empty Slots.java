/* 
You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs are turned off. We turn on exactly one bulb every day until all bulbs are on after n days.

You are given an array bulbs of length n where bulbs[i] = x means that on the (i+1)th day, we will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.

Given an integer k, return the minimum day number such that there exists two turned on bulbs that have exactly k bulbs between them that are all turned off. If there isn't such day, return -1.

 

Example 1:

Input: bulbs = [1,3,2], k = 1
Output: 2
Explanation:
On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
We return 2 because on the second day, there were two on bulbs with one off bulb between them.
Example 2:

Input: bulbs = [1,2,3], k = 1
Output: -1
 

Constraints:

n == bulbs.length
1 <= n <= 2 * 104
1 <= bulbs[i] <= n
bulbs is a permutation of numbers from 1 to n.
0 <= k <= 2 * 104
 */

class Solution {
    static final int INF = Integer.MAX_VALUE;

    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        if (n <= k + 1) {
            return -1;
        }
        int[] order = new int[n];
        for (int i = 0; i < n; ++i) {
            order[bulbs[i] - 1] = i + 1;
        }
        int ans = INF;
        for (int left = 0, right = k + 1; right < n; ) {
            int p = left + 1;
            for (; p < right; ++p) {
                if (order[p] < order[left] || order[p] < order[right]) {
                    break;
                }
            }
            if (p == right) {
                ans = Math.min(ans, Math.max(order[left], order[right]));
            }
            left = p;
            right = left + k + 1;
        }
        return ans == INF ? -1 : ans;
    }
}



class Solution {
    boolean[] on;

    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        if (n <= k + 1) {
            return -1;
        }
        this.on = new boolean[n + 1];
        for (int i = 0; i < n; ++i) {
            if (validate(bulbs[i], k, n)) {
                return i + 1;
            }
        }
        return -1;
    }

    private boolean validate(int pos, int k, int n) {
        on[pos] = true;
        return pos + k + 1 <= n && on[pos + k + 1] && validateHelper(pos + 1, pos + k)
            || pos - k - 1 >= 1 && on[pos - k - 1] && validateHelper(pos - k, pos - 1);
    }

    private boolean validateHelper(int left, int right) {
        for (int i = left; i <= right; ++i) {
            if (on[i]) {
                return false;
            }
        }
        return true;
    }
}