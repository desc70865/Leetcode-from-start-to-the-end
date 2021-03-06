/* 
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 */

class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.merge(num, 1, Integer::sum);
        }
        for (int k: map.keySet()) {
            if (map.get(k) == 1) return k;
        }
        return -1;
    }
}



class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) set.add(num);
        long sum = 0;
        for (int num: set) sum += num;
        sum *= 3;
        for (int num: nums) sum -= num;
        return (int) (sum >> 1);
    }
}

// bitmap

class Solution {
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;
        for (int num: nums) {
            // 0 -> 1 -> 2 -> 0; T = 3
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }
}