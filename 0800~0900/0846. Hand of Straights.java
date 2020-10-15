/* 
Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.
 

Constraints:

1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length
Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */

class Solution {
    public boolean isNStraightHand(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) return false;
        Arrays.sort(nums);
        int[][] map = new int[n][2];
        int idx = 0;
        for (int num: nums) {
            if (idx == 0 || map[idx - 1][0] != num) map[idx++][0] = num;
            else map[idx - 1][1]++;
        }
        int G = n / k;
        for (int i = 0; i < idx && G > 0; i++) {
            int cnt = map[i][1];
            if (cnt == -1) continue;
            if (i + k > idx) return false;
            int num = map[i][0];
            for (int j = 1; j < k; j++) {
                if (map[i + j][0] != num + j) return false;
                if (map[i + j][1] < cnt) return false;
                map[i + j][1] -= cnt + 1;
            }
            G -= cnt + 1;
        }
        return true;
    }
}