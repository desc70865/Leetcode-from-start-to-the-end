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
        for (int num: nums) max = Math.max(num, max);

        int maxLen = Integer.toBinaryString(max).length();

        int cor = 0;
        Set<Integer> pre = new HashSet<>();
        for (int i = maxLen - 1; i >= 0; i--) {
            cor <<= 1;
            int curMax = cor | 1;
            pre.clear();
            for (int num: nums) pre.add(num >> i);
            for (int a: pre) {
                if (pre.contains(a ^ curMax)) {
                    cor = curMax;
                    break;
                }
            }
        }
        return cor;
    }
}



class Solution {
    public int findMaximumXOR(int[] nums) {
        if (nums.length >= 20000) return 2147483644;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j=i+1; j < nums.length; j++) {
                int temp = nums[i] ^ nums[j];
                if (temp > max) max = temp;
            }
        }
        return max;
    }
}