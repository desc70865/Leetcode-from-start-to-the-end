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
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    public void sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }
    
    public int partition(int[] arr, int l, int r) {
        int temp = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r] < temp) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l] > temp) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = temp;
        return l;
    }
}



class Solution {
    public int[] sortArray(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > max) max = nums[i];
            if (nums[i] < min) min = nums[i];
        }
        int[] bucket = new int[max - min + 1];
        for (int j = 0; j < len; j++) {
            bucket[nums[j] - min]++;
        }
        for (int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i - 1];
        }
        int[] ans = new int[len];
        for (int j = len - 1; j >= 0; j--) {
            ans[--bucket[nums[j] - min]] = nums[j];
        }
        return ans;
    }
}