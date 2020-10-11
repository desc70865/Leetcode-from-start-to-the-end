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
        if(n % k != 0) {
            return false;
        }
        List<int[]> numsCnt = new ArrayList<>();
        Arrays.sort(nums);
        for(int num : nums) {
            if(numsCnt.size() == 0 || numsCnt.get(numsCnt.size() - 1)[0] != num) {
                numsCnt.add(new int[]{num, 1});
            } else {
                numsCnt.get(numsCnt.size() - 1)[1]++;
            }
        }
        int m = numsCnt.size();
        int totalGroup = n / k;
        for(int i = 0; i < m; i++) {
            int num = numsCnt.get(i)[0];
            int cnt = numsCnt.get(i)[1];
            if(cnt == 0) {
                continue;
            } else if(i + k > m) {
                return false;
            }
            for(int j = 1; j < k; j++) {
                if(numsCnt.get(i + j)[0] != num + j) {
                    return false;
                }
                if(numsCnt.get(i + j)[1] < cnt) {
                    return false;
                }
                numsCnt.get(i + j)[1] -= cnt;
            }
            totalGroup -= cnt;
            if(totalGroup == 0) {
                return true;
            }
        }
        return true;
    }
}