/* 
You are given two integer arrays nums1 and nums2 of length n.

The XOR sum of the two integer arrays is (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).

For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
Rearrange the elements of nums2 such that the resulting XOR sum is minimized.

Return the XOR sum after the rearrangement.

 

Example 1:

Input: nums1 = [1,2], nums2 = [2,3]
Output: 2
Explanation: Rearrange nums2 so that it becomes [3,2].
The XOR sum is (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2.
Example 2:

Input: nums1 = [1,0,3], nums2 = [5,3,4]
Output: 8
Explanation: Rearrange nums2 so that it becomes [5,4,3]. 
The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.
 

Constraints:

n == nums1.length
n == nums2.length
1 <= n <= 14
0 <= nums1[i], nums2[i] <= 107
 */

class Solution {
    static final int INF = 1_000_000_007;
    
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int m = nums1.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] nums3 = new int[m];
        int[] nums4 = new int[m];
        int n = m;
        for (int i = 0, j = 0, x = 0, y = 0; i < m || j < m; ) {
            if (j == m || i < m && nums1[i] < nums2[j]) {
                nums3[x++] = nums1[i++];
            } else if (i == m || j < m && nums1[i] > nums2[j]) {
                nums4[y++] = nums2[j++];
            } else {
                ++i;
                ++j;
                --n;
            }
        }
        int size = 1 << n;
        int[] dp = new int[size];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int mask = 0; mask < size; ++mask) {
            for (int i = Integer.bitCount(mask), j = 0, slot = 1; j < n; ++j, slot <<= 1) {
                if ((mask & slot) == 0) {
                    dp[mask | slot] = Math.min(dp[mask | slot], dp[mask] + (nums3[i] ^ nums4[j]));
                }
            }
        }
        return dp[size - 1];
    }
}