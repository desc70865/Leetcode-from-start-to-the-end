/* 
The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.
 */

class Solution {
    Set<Integer> set = new HashSet<>();
    public int[] findErrorNums(int[] nums) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < nums.length; i++) {
            a ^= (i + 1);
            b ^= nums[i];
            if (! set.add(nums[i])) c = nums[i];
        }
        return new int[] {c, a ^ b ^ c};
    }
}



class Solution {
    public int[] findErrorNums(int[] nums) {
        int a = 0, b = 0, c = 0;
        int N = nums.length;
        int[] cnt = new int[N];
        for (int i = 0; i < N; i++) {
            a ^= (i + 1);
            b ^= nums[i];
            cnt[nums[i] - 1]++;
        }
        for (int i = 0; i < N; i++) {
            if (cnt[i] == 2) {
                c = i + 1;
                break;
            }
        }
        return new int[] {c, a ^ b ^ c};
    }
}



class Solution {
    public int[] findErrorNums(int[] nums) {
        int a = 0, b = 0, c = 0;
        int N = nums.length;
        int[] cnt = new int[N];
        for (int i = 0; i < N; i++) {
            a ^= (i + 1) ^ nums[i];
            cnt[nums[i] - 1]++;
        }
        for (int i = 0; i < N; i++) {
            if (cnt[i] == 1) continue;
            b = i + 1;
            c = a ^ b;
            if (cnt[i] == 2) return new int[] {b, c};    
            else return new int[] {c, b};
        }
        return new int[] {};
    }
}