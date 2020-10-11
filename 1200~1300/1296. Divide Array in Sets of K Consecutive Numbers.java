/* 
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.

 

Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true
Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length
Note: This question is the same as 846: https://leetcode.com/problems/hand-of-straights/
 */

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int N = nums.length;
        if (N % k != 0) return false;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            if (! map.containsKey(num) || map.get(num) == 0) {
                for (int i = 1; i < k; i++) map.merge(num + i, 1, (a, b) -> a + b);
            } else {
                map.merge(num, -1, (a, b) -> a + b);
            }
        }
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() != 0) return false;
        }
        return true;
    }
}

// 超时

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int N = nums.length;
        if (N % k != 0) return false;
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            int cur = num;
            while (! set.add(cur)) cur += k;
        }
        nums = set.stream().mapToInt(Number::intValue).toArray();
        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));
        for (int i = 0; i < N; i += k) {
            for (int j = i + 1; j < i + k; j++) {
                if (nums[j] - 1 != nums[j - 1]) return false;
            }
        }
        return true;
    }
}

// todo

class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if(n % k != 0) {
            return false;
        }
        List<int[]> numsCnt = new ArrayList<>();
        Arrays.sort(nums);
        for(int num : nums) {
            if(numsCnt.size() == 0 || numsCnt.get(numsCnt.size() - 1)[0] != num) {
                numsCnt.add(new int[]{num, 1});
            } else {
                numsCnt.get(numsCnt.size() - 1)[1]++;
            }
        }
        int m = numsCnt.size();
        int totalGroup = n / k;
        for(int i = 0; i < m; i++) {
            int num = numsCnt.get(i)[0];
            int cnt = numsCnt.get(i)[1];
            if(cnt == 0) {
                continue;
            } else if(i + k > m) {
                return false;
            }
            for(int j = 1; j < k; j++) {
                if(numsCnt.get(i + j)[0] != num + j) {
                    return false;
                }
                if(numsCnt.get(i + j)[1] < cnt) {
                    return false;
                }
                numsCnt.get(i + j)[1] -= cnt;
            }
            totalGroup -= cnt;
            if(totalGroup == 0) {
                return true;
            }
        }
        return true;
    }
}