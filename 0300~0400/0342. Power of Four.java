/* 
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example 1:

Input: 16
Output: true
Example 2:

Input: 5
Output: false
Follow up: Could you solve it without loops/recursion?
 */

class Solution {
    public boolean isPowerOfFour(int num) {
        
    }
}



class Solution {
    private int[] LIST = new int[]{
                 1,           4,         16,
                64,         256,       1024,
              4096,       16384,      65536,
            262144,     1048576,    4194304,
          16777216,    67108864,  268435456,
        1073741824};
    
    public boolean isPowerOfFour(int n) {
        if (n <= 0 || (n & (n - 1)) != 0) {
            return false;
        }
        for (int num: LIST) {
            if (n == num) {
                return true;
            }
        }
        return false;
    }
}



class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0 || (n & (n - 1)) != 0) {
            return false;
        }
        int tail = n % 10;
        return tail == 4 || tail == 6 || n == 1;
    }
}



class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & n - 1) == 0 && (n & 0x55555555) != 0;
    }
}