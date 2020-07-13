/* 
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.
 */

class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }
    
    private int hammingDistance(int x, int y) {
        return hammingWeight(x ^ y);
    }
    
    private int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}



class Solution {
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int zero = 0, one = 0;
            for (int n: nums) {
                if ((n & 1 << i) == (1 << i)) {
                    one++;
                } else {
                    zero++;
                }
            }
            ans += zero * one;
        }
        return ans;
    }
}



class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0, LEN = nums.length;
        int[] cnt = new int[32];
        
        for (int num: nums) {
            for (int i = 0; i < 32; i++) {
                cnt[i] += num & 1;
                num >>= 1;
            }
        }
        
        for (int ones: cnt) {
            res += ones * (LEN - ones);
        }
        return res;
    }
}



class Solution {
    public int totalHammingDistance(int[] nums) {
        int LEN = nums.length, cnt = 0, total = 0;
        for (int j = 0, b = 1; j < 31; j++, b <<= 1) {
            cnt = 0;
            for (int i = 0; i < LEN; i++) {
                cnt += (nums[i] & b) >> j;
            }
            total += (LEN - cnt) * cnt;
        }
        return total;
    }
}