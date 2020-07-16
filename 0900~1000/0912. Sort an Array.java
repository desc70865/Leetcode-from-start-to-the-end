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
        sort(nums, 0, nums.length-1);
        return nums;
    }
    
    private void sort(int[] arr, int l, int r) {
        int LEN = r - l;
        if (LEN < 0) {
            return;
        }
        int pivot = partition(arr, l, r);
        sort(arr, l, pivot-1);
        sort(arr, pivot+1, r);
    }
    
    private int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r] < pivot) {
                    arr[l] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l] > pivot) {
                    arr[r] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }
}



class Solution {
    public int[] sortArray(int[] nums) {
       	nums = CountingSort(nums);
		return nums;
	}

	public int[] CountingSort(int[] num) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int i = 0; i < num.length; i++) {
			if (num[i] > max)
				max = num[i];
			if (num[i] < min)
				min = num[i];
		}

		int[] C = new int[max - min + 1];
		int[] B = new int[num.length];
		for (int i = 0; i < C.length; i++) {
			C[i] = 0;
		}

		for (int j = 0; j < num.length; j++) {
			C[num[j] - min]++;
		}

		for (int i = 1; i < C.length; i++) {
			C[i] = C[i] + C[i - 1];
		}

		for (int j = num.length - 1; j >= 0; j--) {
			B[--C[num[j] - min]] = num[j];
		}

		return B;
	}
}