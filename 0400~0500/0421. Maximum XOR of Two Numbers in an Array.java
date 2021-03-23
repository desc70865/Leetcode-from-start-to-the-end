/* 
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */

public class Solution {
    public int findMaximumXOR(int[] nums) {
        int res = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            mask |= 1 << i;
            Set<Integer> prefix = new HashSet<>();
            for (int num: nums) {
                prefix.add(num & mask);
            }
            int cur = res | (1 << i);
            for (Integer p: prefix) {
                if (prefix.contains(p ^ cur)) {
                    res = cur;
                    break;
                }
            }
        }
        return res;
    }
}



class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int num: nums) {
            max = Math.max(max, num);
        }
        int len = 32 - Integer.numberOfLeadingZeros(max);
        Set<Integer> prefix = new HashSet<>();
        int maxXOR = 0;
        for (int i = len - 1; i >= 0; i--) {
            maxXOR <<= 1;
            int curPrefix = maxXOR | 1;
            prefix.clear();
            for (int num: nums) {
                prefix.add(num >> i);
                if (prefix.contains(num >> i ^ curPrefix)) {
                    maxXOR |= 1;
                    break;
                }
            }
        }
        return maxXOR;
    }
}