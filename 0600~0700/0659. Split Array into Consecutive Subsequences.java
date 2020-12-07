/* 
Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.

 

Example 1:

Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5
Example 2:

Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5
Example 3:

Input: [1,2,3,4,4,5]
Output: False
 

Constraints:

1 <= nums.length <= 10000
 */

class Solution {
    public boolean isPossible(int[] nums) {
        int n = nums.length, i = 0;
        if (n < 3) return false;
        int one = 0, two = 0, more = 0;
        while (i < n && nums[i] == nums[0]) {
            one++;
            i++;
        }
        while (i < n) {
            int pre = nums[i - 1];
            if (nums[i] - pre > 1) {
                if (one > 0 || two > 0) {
                    return false;
                }
                more = 0;
                int start = nums[i];
                while (i < n && nums[i] == start) {
                    one++;
                    i++;
                }
            } else {
                int count = 0;
                while (i < n && nums[i] == pre + 1) {
                    count++;
                    i++;
                }
                int remain = count - one - two, temp;
                if (remain < 0) {
                    return false;
                } else {
                    temp = remain - more;
                    more = Math.min(remain, more) + two;
                }
                two = one;
                one = Math.max(0, temp);
            }
        }
        return one + two == 0;
    }
}



class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        for (int num: nums) {
            countMap.merge(num, 1, Integer::sum);
        }
        for (int num: nums) {
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {
                int prevEndCount = endMap.getOrDefault(num - 1, 0);
                if (prevEndCount > 0) {
                    countMap.put(num, count - 1);
                    endMap.put(num - 1, prevEndCount - 1);
                    endMap.merge(num, 1, Integer::sum);
                } else {
                    int count1 = countMap.getOrDefault(num + 1, 0);
                    int count2 = countMap.getOrDefault(num + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, count1 - 1);
                        countMap.put(num + 2, count2 - 1);
                        endMap.merge(num + 2, 1, Integer::sum);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}



class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num: nums) {
            map.putIfAbsent(num, new PriorityQueue<>());
            if (map.containsKey(num - 1)) {
                int prevLength = map.get(num - 1).poll();
                if (map.get(num - 1).isEmpty()) {
                    map.remove(num - 1);
                }
                map.get(num).offer(prevLength + 1);
            } else {
                map.get(num).offer(1);
            }
        }
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            if (entry.getValue().peek() < 3) {
                return false;
            }
        }
        return true;
    }
}