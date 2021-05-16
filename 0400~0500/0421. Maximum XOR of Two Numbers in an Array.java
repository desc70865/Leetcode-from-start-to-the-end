/* 
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */

class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int num: nums) {
            max = Math.max(max, num);
        }
        int len = 32 - Integer.numberOfLeadingZeros(max);
        Set<Integer> prefixSet = new HashSet<>();
        int mask = 0;
        for (int i = len - 1; i >= 0; --i) {
            mask <<= 1;
            int prefix = mask | 1;
            prefixSet.clear();
            for (int num: nums) {
                int cur = num >> i;
                if (prefixSet.contains(cur ^ prefix)) {
                    mask |= 1;
                    break;
                }
                prefixSet.add(cur);
            }
        }
        return mask;
    }
}