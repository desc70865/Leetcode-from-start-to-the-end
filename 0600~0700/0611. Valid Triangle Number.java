/* 
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
 */

class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (isTriangle(nums[i], nums[j], nums[k])) sum++;
                    else break;
                }
            }
        }
        return sum;
    }

    private boolean isTriangle(int a, int b, int c) {
        return a + b > c;
    }
}



class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                sum += binarySearch(nums, nums[i] + nums[j], j + 1, len - 1) - j;
            }
        }
        return sum;
    }

    // @return I dont what to say
    private int binarySearch(int[] A, int target, int L, int R) {
        while (L <= R) {
            int M = L + R >> 1;
            if (A[M] >= target) R = M - 1;
            else L = M + 1;
        }
        return L - 1;
    }
}



public class Solution {
    private static int combinationOf3(int n) {
        if (n < 3) {
            return 0;
        } else if (n == 3) {
            return 1;
        } else {
            return n * (n - 1) * (n - 2) / 3 / 2;
        }
    }

    private static int combinationOf2(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return n * (n - 1) / 2;
        }
    }

    public int triangleNumber(int[] nums) {
        int[] counters = new int[1001];
        int[] records = new int[1001];

        int maxLen = 0;
        int minLen = 1000;
        for (int num : nums) {
            records[num]++;
            maxLen = Math.max(maxLen, num);
            minLen = Math.min(minLen, num);
        }
        minLen = Math.max(minLen, 1);
        counters[minLen] = records[minLen];
        for (int i = minLen + 1; i <= maxLen; i++) {
            counters[i] = counters[i - 1] + records[i];
        }
        int total = 0;
        for (int i = minLen; i <= maxLen; i++) {
            if (records[i] == 0) {
                continue;
            }
            total += combinationOf3(records[i]);
            if (records[i] >= 2) {
                int r = (i << 1) - 1;
                r = Math.min(r, maxLen);
                total += (counters[r] - counters[i]) * combinationOf2(records[i]);
            }
            for (int j = i + 1; j <= maxLen; j++) {
                if (records[j] == 0) {
                    continue;
                }
                int r = i + j - 1;
                r = Math.min(r, maxLen);
                total += combinationOf2(records[j]) * records[i];
                total += records[i] * records[j] * (counters[r] - counters[j]);
            }
        }
        return total;
    }
}