/* 
You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.

A number x is considered missing if x is in the range [lower, upper] and x is not in nums.

Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b
 

Example 1:

Input: nums = [0,1,3,50,75], lower = 0, upper = 99
Output: ["2","4->49","51->74","76->99"]
Explanation: The ranges are:
[2,2] --> "2"
[4,49] --> "4->49"
[51,74] --> "51->74"
[76,99] --> "76->99"
Example 2:

Input: nums = [], lower = 1, upper = 1
Output: ["1"]
Explanation: The only missing range is [1,1], which becomes "1".
Example 3:

Input: nums = [], lower = -3, upper = -1
Output: ["-3->-1"]
Explanation: The only missing range is [-3,-1], which becomes "-3->-1".
Example 4:

Input: nums = [-1], lower = -1, upper = -1
Output: []
Explanation: There are no missing ranges since there are no missing numbers.
Example 5:

Input: nums = [-1], lower = -2, upper = -1
Output: ["-2"]
 

Constraints:

-109 <= lower <= upper <= 109
0 <= nums.length <= 100
lower <= nums[i] <= upper
All the values of nums are unique.
 */

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        long pre = (long) lower - 1;    // 防止 lower 就是 int 最小值，减一就直接溢出了
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - pre == 2) {
                ans.add(String.valueOf(pre + 1));
            } else if (nums[i] - pre >= 3) {
                ans.add((pre + 1) + "->" + (nums[i] - 1));
            }
            pre = nums[i]; // 'int' to 'long'
        }
        if (upper - pre == 1) {
            ans.add(String.valueOf(pre + 1));
        } else if (upper - pre >= 2) {
            ans.add((pre + 1) + "->" + upper);
        }
        return ans;
    }
}



class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            if (lower == upper) {
                res.add(String.valueOf(lower));
            } else {
                res.add(new StringBuilder().append(lower).append("->").append(upper).toString());
            }

            return res;
        }

        Integer pre = nums[0];
        Integer cur = null;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                int interval = nums[i] - lower;
                if (interval == 0) {
                    continue;
                }

                if (interval == 1) {
                    res.add(String.valueOf(lower));
                }

                if (interval > 1) {
                    res.add(new StringBuilder().append(lower).append("->").append(nums[0] - 1).toString());
                }
            } else {
                cur = nums[i];
                int interval = cur - pre;
                if (interval == 1) {
                    pre = cur;
                    continue;
                } else if(interval > 2){
                    res.add(new StringBuilder().append(String.valueOf(pre + 1)).append("->").append(String.valueOf(cur - 1)).toString());
                } else if (interval == 2) {
                    res.add(String.valueOf(pre + 1));
                }
                pre = cur;
            }
        }
        int lastNum = nums[nums.length - 1];
        if (upper - lastNum == 1) {
            res.add(String.valueOf(upper));
        }

        if (upper - lastNum > 1) {
            res.add(new StringBuilder().append(String.valueOf(lastNum + 1)).append("->").append(String.valueOf(upper)).toString());
        }

        return res;
    }
}