/* 
Given an array of integers nums, sort the array in ascending order.

 

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
 

Constraints:

1 <= nums.length <= 50000
-50000 <= nums[i] <= 50000
 */

class Solution {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
}



class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    public void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }
    
    public int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (cmp(arr[r], pivot)) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (cmp(pivot, arr[l])) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }

    private boolean cmp(int a, int b) {
        return a < b;
    }
}



class Solution {
    public int[] sortArray(int[] nums) {
        int min = 1 << 16, max = 0 - min;
        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int[] bucket = new int[max - min + 1];
        for (int num: nums) {
            bucket[num - min]++;
        }
        for (int i = 1; i <= max - min; i++) {
            bucket[i] += bucket[i - 1];
        }
        int[] ans = new int[nums.length];
        for (int num: nums) {
            ans[--bucket[num - min]] = num;
        }
        // @Stability
        // for (int i = nums.length - 1; i >= 0; i--) {
        //     ans[--bucket[nums[i] - min]] = nums[i];
        // }
        return ans;
    }
}