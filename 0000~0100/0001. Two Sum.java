/* 
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
Given nums = [2, 7, 11, 15], target = 9,
=======

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */
 
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int LEN = nums.length;
        int[] arr = Arrays.copyOf(nums, LEN);
        Arrays.sort(nums);
        int i = 0, j = LEN - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                break;
            }
        }
        int t = i;
        for (int k = 0; k < LEN; k++) {
            if (arr[k] == nums[i]) {
                i = k;
                break;
            }
        }
        /* for (int k = 0; k < LEN; k++) {
            if (arr[k] == nums[j] && k != i) {
                j = k;
                break;
            }
        } */
        for (int k = (t == j) ? i + 1 : 0; k < LEN; k++) {
            if (arr[k] == nums[j]) {
                j = k;
                break;
            }
        }
        return new int[] {i, j};
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int bitMode = 2047, c = 0;
        int[] res = new int[bitMode + 1];
        
        for (int i = 0; i < nums.length; i++) {
            c = (target - nums[i]) & bitMode;
            if (res[c] > 0) return new int[]{ res[c]-1, i };
            res[nums[i] & bitMode] = i + 1;
        }
        return null;
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int LEN = nums.length;
        int[][] arr = new int[LEN][2];
        for (int i = 0; i < LEN; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (x, y) -> (x[0] - y[0]));
        // Arrays.sort(arr, new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] x, int[] y) {
        //         return x[0] - y[0];
        //     }
        // });
        // sort(arr, 0, LEN - 1);
        int i = 0, j = LEN - 1;
        while (i < j) {
            int sum = arr[i][0] + arr[j][0];
            if (sum < target) i++;
            else if (sum > target) j--;
            else return new int[] { arr[i][1], arr[j][1] };
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
    /* private void sort(int[][] arr, int l, int r) {
        int LEN = r - l;
        if (LEN < 0) {
            return;
        }
        int pivot = partition(arr, l, r);
        sort(arr, l, pivot-1);
        sort(arr, pivot+1, r);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int pivot = arr[l][0], index = arr[l][1];
        while (l < r) {
            while (l < r) {
                if (arr[r][0] < pivot) {
                    arr[l][0] = arr[r][0];
                    arr[l][1] = arr[r][1];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l][0] > pivot) {
                    arr[r][0] = arr[l][0];
                    arr[r][1] = arr[l][1];
                    break;
                }
                l++;
            }
        }
        arr[l][0] = pivot; arr[l][1] = index;
        return l;
    } */
}