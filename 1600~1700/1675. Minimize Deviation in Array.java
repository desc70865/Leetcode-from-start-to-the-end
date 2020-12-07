/* 
You are given an array nums of n positive integers.

You can perform two types of operations on any element of the array any number of times:

If the element is even, divide it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
If the element is odd, multiply it by 2.
For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
The deviation of the array is the maximum difference between any two elements in the array.

Return the minimum deviation the array can have after performing some number of operations.

 

Example 1:

Input: nums = [1,2,3,4]
Output: 1
Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
Example 2:

Input: nums = [4,1,5,20,3]
Output: 3
Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
Example 3:

Input: nums = [2,10,8]
Output: 3
 

Constraints:

n == nums.length
2 <= n <= 105
1 <= nums[i] <= 109
 */

class Solution {
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num: nums) {
            set.add(num % 2 == 0 ? num : num * 2);
        }
        int res = set.last() - set.first();
        while (res > 0 && set.last() % 2 == 0) {
            int max = set.last();
            set.remove(max);
            set.add(max / 2);
            res = Math.min(res, set.last() - set.first());
        }
        return res;
    }
}



class Solution {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        
        int min = (int) (1e9 + 1);
        int max = 0;
        for (int val: nums) {
            int rest = 0;
            if ((val & 1) == 1) {
                rest = 1;
            } else {
                while ((val & 1) == 0) {
                    val >>= 1;
                    rest++;
                }
            }
            pq.offer(new int[] {val, rest});
            
            min = Math.min(val, min);
            max = Math.max(val, max);
        }
        
        int res = max - min;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            res = Math.min(res, max - cur[0]);
            if (cur[1] == 0) {
                break;
            }
            cur[0] <<= 1;
            cur[1]--;
            pq.offer(cur);
            max = Math.max(cur[0], max);
        }
        return res;
    }
}