/* 
Given an array of integers nums.

A pair (i,j) is called good if nums[i] == nums[j] and i < j.

Return the number of good pairs.

 

Example 1:

Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
Example 2:

Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.
Example 3:

Input: nums = [1,2,3]
Output: 0
 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
 */

class Solution {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num: nums) {
            map.compute(num, (k, v) -> {
                if (v == null) {
                    v = 1;
                } else {
                    v += 1;
                }
                return v;
            });
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res += factorial(entry.getValue());
        }
        
        return res;
    }
    
    private int factorial(int x) {
        return x * (x - 1) / 2;
    }
}



class Solution {
    public int numIdenticalPairs(int[] nums) {
        Arrays.sort(nums);
        int res = 0, cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                res += ++cnt;
            } else {
                cnt = 0;
            }
        }
        return res;
    }
}



class Solution {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> ints = new HashMap<Integer, Integer>();
        int pairs = 0;
        for (int i = 0; i < nums.length; i++) {
            if (ints.containsKey(nums[i])) {
                pairs += ints.get(nums[i]);
                ints.put(nums[i], ints.get(nums[i]) + 1);
            } else {
                ints.put(nums[i], 1);
            }
        }
        return pairs;
    }
}