/* 
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 1;
        for (; i > 0 && nums[i - 1] >= nums[i]; --i);
        if (i == 0) {
            reverse(nums, i, n - 1);
            return;
        }
        int j = n - 1;
        for (; j >= 0 && nums[i - 1] >= nums[j]; --j);
        swap(nums, i - 1, j);
        reverse(nums, i, n - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        for (; l < r; swap(nums, l++, r--));
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// 也许构建全排列组合直接查找会更有趣?
// 观察规律,找到从后往前第一个减小的项: while > && i != 0; --i
// then 找到第一个更大的值
// 交换 i 与 j
// 翻转 i 之后的子串