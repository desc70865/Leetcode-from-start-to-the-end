/* 
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
 */

class Solution {
    Random rand;
    int n;
    int[][] nums;
    
    public Solution(int[] nums) {
        this.rand = new Random();
        this.n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        quickSort(arr, 0, n - 1);
        this.nums = arr;
    }
    
    public int pick(int target) {
        int cnt = 0, ans = 0;
        int left = binarySearch(target);
        for (int i = left; i < nums.length && nums[i][0] == target; ++i) {
            if (rand.nextInt(++cnt) == 0) {
                ans = nums[i][1];
            }
        }
        return ans;
    }

    private int binarySearch(int target) {
        int L = 0, R = n - 1;
        while (L < R) {
            int m = L + R >> 1;
            if (nums[m][0] >= target) R = m;
            else L = m + 1;
        }
        return L;
    }

    private void quickSort(int[][] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int[] pivot = arr[l];
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

    private boolean cmp(int[] a, int[] b) {
        return a[0] == b[0] ? a[1] < b[1] : a[0] < b[0];
    }
}



class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    Random rand;

    public Solution(int[] nums) {
        this.rand = new Random();
        for (int i = 0; i < nums.length; ++i) {
            map.computeIfAbsent(nums[i], z -> new ArrayList<>()).add(i);
        }
    }
    
    public int pick(int target) {
        int cnt = 0, ans = 0;
        for (int k: map.get(target)) {
            if (rand.nextInt(++cnt) == 0) {
                ans = k;
            }
        }
        return ans;
    }
}



class Solution {
    int[] nums;
    Random rand;
    
    public Solution(int[] nums) {
        this.rand = new Random();
        this.nums = nums;
    }
    
    public int pick(int target) {
        int cnt = 0, res = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target && rand.nextInt(++cnt) == 0) {
                res = i;
            }
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */