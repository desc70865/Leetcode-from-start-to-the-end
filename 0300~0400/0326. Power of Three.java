/* 
Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
Follow up:
Could you do it without using any loop / recursion?
 */

class Solution {
    // pow of 2, 2^0 - 2 ^31
    private int[] LIST = new int[31]{
                 1,           2,           4,           8,         16,          32, 
                64,         128,         256,         512,       1024,        2048, 
              4096,        8192,       16384,       32768,      65536,      131072, 
            262144,      524288,     1048576,     2097152,    4194304,     8388608, 
          16777216,    33554432,    67108864,   134217728,  268435456,   536870912, 
        1073741824};
    
    public boolean isPowerOfThree(int n) {
        return n <= 0 ? false : (n & (n - 1)) == 0 && BitLength(n);
    }
    
    private boolean BitLength(int n) {

        int left = 0;
        int right = 30;

        while (left <= right) { 
            int mid = (left + right) / 2;

            if (LIST[mid] <= n) { 
                if (LIST[mid + 1] > n) {
                    return mid % 2 == 0;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        // not found
        return false;
    }
}



class Solution {
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }
}



class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}