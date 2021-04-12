/* 
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
 */

class Solution {
    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] str = new String[len];
        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str, (a, b) -> (b + a).compareTo(a + b));
        if (str[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < len; j++) {
            sb.append(str[j]);
        }
        return sb.toString();
    }
}



class Solution {
    public String largestNumber(int[] nums) {
        sort(nums, 0, nums.length - 1);
        if (nums[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int num: nums) {
            sb.append(num);
        }
        return sb.toString();
    }
    
    public void sort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
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
        long A = a * 10L, B = b * 10L; // 至少左移一位
        int _a = a, _b = b;
        while (_a >= 10) {
            B *= 10;
            _a /= 10;
        }
        while (_b >= 10) {
            A *= 10;
            _b /= 10;
        }
        return (A + b) > (B + a);
    }
}