/* 
Given an array of integers A, consider all non-empty subsequences of A.

For any sequence S, let the width of S be the difference between the maximum and minimum element of S.

Return the sum of the widths of all subsequences of A. 

As the answer may be very large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: [2,1,3]
Output: 6
Explanation:
Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
The sum of these widths is 6.
 

Note:

1 <= A.length <= 20000
1 <= A[i] <= 20000
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/sum-of-subsequence-widths/solution/0891-tong-pai-xu-ji-xian-you-hua-by-keyl-wlrq/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    static final int MOD = 1_000_000_007;
    
    public int sumSubseqWidths(int[] A) {
        int len = A.length;
        Arrays.sort(A);
        long sum = 0;
        for (int i = 0, p = 1; i < len; i++) {
            sum += (long) (A[i] - A[len - 1 - i]) * p;
            p <<= 1;
            sum %= MOD;
            p %= MOD;
        }
        return (int) ((sum + MOD) % MOD);
    }
}



class Solution {
    static final int MOD = 1_000_000_007;
    
    public int sumSubseqWidths(int[] A) {
        int len = A.length;
        A = sort(A);
        long sum = 0;
        for (int i = 0, p = 1; i < len; i++) {
            sum += (long) (A[i] - A[len - 1 - i]) * p;
            p <<= 1;
            sum %= MOD;
            p %= MOD;
        }
        return (int) ((sum + MOD) % MOD);
    }

    private int[] sort(int[] nums) {
        int size = 0;
        for (int num: nums) {
            size = Math.max(size, num);
        }
        int[] bucket = new int[size + 1];
        for (int num: nums) {
            bucket[num]++;
        }
        int[] ans = new int[len];
        int idx = 0;
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                ans[idx++] = i;
            }
        }
        return ans;
    }
}