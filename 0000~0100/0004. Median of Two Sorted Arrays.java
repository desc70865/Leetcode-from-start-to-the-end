/* 
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.
Example 1:
nums1 = [1, 3]
nums2 = [2]
The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
=======

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0

Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2; // 更新 A 中点近似
            int j = halfLen - i; // ~ B
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; } // length odd, maxLeft == mid

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}

// 同步搜索两个数组的分割点以确定中位数

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] tmp = merge(nums1, nums2);
        int N = tmp.length;
        if (N % 2 == 1) return tmp[N / 2];
        else return ((double) tmp[N / 2 - 1] + (double) tmp[N / 2]) / 2;
    }

    private int[] merge(int[] A, int[] B) {
        int M = A.length, N = B.length;
        if (M == 0) return B;
        else if (N == 0) return A;
        int[] res = new int[M + N];
        int i = M - 1, j = N - 1, k = M + N - 1;
        while (k >= 0) {
            res[k--] = (j < 0 || i >= 0 && A[i] > B[j]) ? A[i--] : B[j--];
        }
        return res;
    }
}