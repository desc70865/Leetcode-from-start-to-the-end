/* 
Given two arrays of integers nums1 and nums2, return the number of triplets formed (type 1 and type 2) under the following rules:

Type 1: Triplet (i, j, k) if nums1[i]2 == nums2[j] * nums2[k] where 0 <= i < nums1.length and 0 <= j < k < nums2.length.
Type 2: Triplet (i, j, k) if nums2[i]2 == nums1[j] * nums1[k] where 0 <= i < nums2.length and 0 <= j < k < nums1.length.
 

Example 1:

Input: nums1 = [7,4], nums2 = [5,2,8,9]
Output: 1
Explanation: Type 1: (1,1,2), nums1[1]^2 = nums2[1] * nums2[2]. (4^2 = 2 * 8). 
Example 2:

Input: nums1 = [1,1], nums2 = [1,1,1]
Output: 9
Explanation: All Triplets are valid, because 1^2 = 1 * 1.
Type 1: (0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2).  nums1[i]^2 = nums2[j] * nums2[k].
Type 2: (0,0,1), (1,0,1), (2,0,1). nums2[i]^2 = nums1[j] * nums1[k].
Example 3:

Input: nums1 = [7,7,8,3], nums2 = [1,2,9,7]
Output: 2
Explanation: There are 2 valid triplets.
Type 1: (3,0,2).  nums1[3]^2 = nums2[0] * nums2[2].
Type 2: (3,0,1).  nums2[3]^2 = nums1[0] * nums1[1].
Example 4:

Input: nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18]
Output: 0
Explanation: There are no valid triplets.
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
1 <= nums1[i], nums2[i] <= 10^5
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/solution/gei-ye-pa-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    int res;
    public int numTriplets(int[] nums1, int[] nums2) {
        res = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        helper(nums1, nums2);
        helper(nums2, nums1);
        return res;
    }

    private void helper(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int pre = 0;
        for (int i = 0; i < m; i++) {
            if (i > 0 && A[i] == A[i - 1]) {
                res += pre;
                continue;
            }
            pre = 0;
            long sum = (long) A[i] * A[i];
            int L = 0;
            int R = n - 1;
            while (L < R) {
                long k = (long) B[L] * B[R] - sum;
                if (k == 0) {
                    if (B[L] == B[R]) {
                        pre += (1 + R - L) * (R - L) / 2;
                        break;
                    } else {
                        int x = 1;
                        int y = 1;
                        while (L < R - 1 && B[L] == B[L + 1]) {
                            L++;
                            x++;
                        }
                        while (L + 1 < R && B[R] == B[R - 1]) {
                            R--;
                            y++;
                        }
                        pre += x * y;
                        L++;
                        R--;
                    }
                } else if (k < 0) L++;
                else R--;
            }
            res += pre;
        }
    }
}