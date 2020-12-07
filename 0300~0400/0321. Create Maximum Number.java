/* 
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:

Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]
Example 2:

Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]
Example 3:

Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]
 */

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 + len2 == k) {
            return merge(nums1, nums2);
        }
        int[] ans = new int[k];
        for (int i = Math.max(0, k - len2); i <= Math.min(len1, k); i++) {
            int[] a = getMaxSubarr(nums1, i);
            int[] b = getMaxSubarr(nums2, k - i);
            int[] c = merge(a, b);
            if (compare(c, 0, ans, 0)) ans = c;
        }
        return ans;
    }

    private int[] getMaxSubarr(int[] nums, int k) {
        int[] stack = new int[k];
        int len = nums.length;
        int idx = -1;
        int rem = len - k;
        for (int i = 0; i < len; i++) {
            while (idx >= 0 && stack[idx] < nums[i] && rem > 0) {
                idx--;
                rem--;
            }
            if (idx < k - 1) {
                stack[++idx] = nums[i];
            } else {
                rem--;
            }
        }
        return stack;
    }

    private int[] merge(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        if (len1 == 0) return arr2;
        int len2 = arr2.length;
        if (len2 == 0) return arr1;
        int[] sum = new int[len1 + len2];
        int i = 0;
        int j = 0;
        int k = 0;
        for (int p = 0; p < len1 + len2; p++) {
            if (compare(arr1, i, arr2, j)) {
                sum[k++] = arr1[i++];
            } else {
                sum[k++] = arr2[j++];
            }
        }
        // System.out.println(Arrays.toString(arr1));
        // System.out.println(Arrays.toString(arr2));
        // System.out.println(Arrays.toString(sum));
        // System.out.println();
        return sum;
    }

    private boolean compare(int[] arr1, int a, int[] arr2, int b) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        while (a < len1 && b < len2) {
            int diff = arr1[a++] - arr2[b++];
            if (diff != 0) return diff > 0;
        }
        return (len1 - a) - (len2 - b) > 0;
    }
}