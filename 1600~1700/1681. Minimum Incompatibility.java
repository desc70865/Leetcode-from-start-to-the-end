/* 
You are given an integer array nums​​​ and an integer k. You are asked to distribute this array into k subsets of equal size such that there are no two equal elements in the same subset.

A subset's incompatibility is the difference between the maximum and minimum elements in that array.

Return the minimum possible sum of incompatibilities of the k subsets after distributing the array optimally, or return -1 if it is not possible.

A subset is a group integers that appear in the array with no particular order.

 

Example 1:

Input: nums = [1,2,1,4], k = 2
Output: 4
Explanation: The optimal distribution of subsets is [1,2] and [1,4].
The incompatibility is (2-1) + (4-1) = 4.
Note that [1,1] and [2,4] would result in a smaller sum, but the first subset contains 2 equal elements.
Example 2:

Input: nums = [6,3,8,1,3,1,2,2], k = 4
Output: 6
Explanation: The optimal distribution of subsets is [1,2], [2,3], [6,8], and [1,3].
The incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
Example 3:

Input: nums = [5,3,3,6,3,3], k = 3
Output: -1
Explanation: It is impossible to distribute nums into 3 subsets where no two elements are equal in the same subset.
 

Constraints:

1 <= k <= nums.length <= 16
nums.length is divisible by k
1 <= nums[i] <= nums.length
 */

class Solution {
    int res, k, cnt;
    int[] idx, latest, first, nums;

    public int minimumIncompatibility(int[] nums, int k) {
        if (nums.length == k) {
            return 0;
        }
        int[] numCnt = new int[17];
        for (int num : nums) {
            if ((++numCnt[num]) > k) {
                return -1;
            }
        }
        Arrays.sort(nums);
        this.k = k;
        this.nums = nums;
        cnt = nums.length / k;
        res = Integer.MAX_VALUE;
        idx = new int[k];
        latest = new int[k];
        first = new int[k];
        dfs(0, 0);
        return res;
    }


    private void dfs(int used, int sum) {
        if (used == nums.length) {
            res = Math.min(res, sum);
            return;
        }
        if (sum >= res) {
            return;
        }
        for (int i = 0; i < k; i++) {
            if (idx[i] < cnt && (idx[i] == 0 || latest[i] != nums[used])) {
                int newSum = sum;
                if (idx[i] == 0) {
                    first[i] = nums[used];
                } else {
                    newSum -= latest[i] - first[i];
                    newSum += nums[used] - first[i];
                }
                idx[i]++;
                int bak = latest[i];
                latest[i] = nums[used];
                dfs(used + 1, newSum);
                idx[i]--;
                latest[i] = bak;
                if (idx[i] == 0) {
                    break;
                }
            }
        }
    }
}



class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int length = nums.length;
        int groupSize = length/k;
        int[][] groups = new int[k][groupSize];
        int[] counts = new int[length];
        fillCounts(counts, nums);
        if (possible(counts, k, 0)) {
            fillGroupsMin(groups, 0, counts);
            int op1 = score(groups);
            groups = new int[k][groupSize];
            fillCounts(counts, nums);
            fillGroupsMax(groups, 0, counts);
            int op2 = score(groups);
            return Math.min(op1, op2);
        } else {
            return -1;
        }
    }
    
    private void fillGroupsMin(int[][] groups, int group, int[] counts) {
        int numGroups = groups.length;
        int groupSize = groups[0].length;
        int min = 1;
        for (int spot = 0; spot < groupSize; spot++) {
            while (groups[group][spot] == 0) {
                int minPres = min(counts, min);
                counts[minPres-1]--;
                if (possible(counts, numGroups-group-1, groupSize-spot-1)) {
                    groups[group][spot] = minPres;
                } else {
                    counts[minPres-1]++;
                }
                min = minPres+1;
            }
        }
        if (group+1 < numGroups) {
            fillGroupsMin(groups, group+1, counts);
        }
    }
    
    private void fillGroupsMax(int[][] groups, int group, int[] counts) {
        int numGroups = groups.length;
        int groupSize = groups[0].length;
        int max = counts.length;
        for (int spot = 0; spot < groupSize; spot++) {
            while (groups[group][spot] == 0) {
                int maxPres = max(counts, max);
                counts[maxPres-1]--;
                if (possible(counts, numGroups-group-1, groupSize-spot-1)) {
                    groups[group][spot] = maxPres;
                } else {
                    counts[maxPres-1]++;
                }
                max = maxPres-1;
            }
        }
        if (group+1 < numGroups) {
            fillGroupsMax(groups, group+1, counts);
        }
    }
    
    private int min(int[] counts, int min) {
        for (int i = min-1; i < counts.length; i++) {
            if (counts[i] > 0) {
                return i+1;
            }
        }
        return -1;
    }
    
    private int max(int[] counts, int max) {
        for (int i = max-1; i >= 0; i--) {
            if (counts[i] > 0) {
                return i+1;
            }
        }
        return -1;
    }
    
    private int score(int[][] groups) {
        int score = 0;
        for (int[] group : groups) {
            score += score(group);
        }
        return score;
    }
    
    private int score(int[] group) {
        int min = 16;
        int max = 1;
        for (int num : group) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        return max - min;
    }
    
    private void fillCounts(int[] counts, int[] nums) {
        for (int num : nums) {
            counts[num-1]++;
        }
    }
    
    private boolean possible(int[] counts, int freeGroups, int extraSpots) {
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (count > freeGroups) {
                if (count > freeGroups+1) {
                    return false;
                } else {
                    extraSpots--;
                    if (extraSpots < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}