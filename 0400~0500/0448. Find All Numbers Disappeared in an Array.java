/* 
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
 */

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int LEN = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= LEN; i++) {
            set.add(i);
        }
        for (int num: nums) {
            set.remove(num);
        }
        
        return new ArrayList<>(set);
    }
}



class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int LEN = nums.length;
        int[] aux = new int[LEN+1];
        for (int i = 1; i <= LEN; i++) {
            aux[i] = i;
        }
        for (int num: nums) {
            aux[num] = 0;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int num: aux) {
            if (num != 0) {
                list.add(num);
            }
        }
        return list;
    }
}



class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int LEN = nums.length;
        boolean[] aux = new boolean[LEN+1];
        for (int num: nums) {
            aux[num] = true;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= LEN; i++) {
            if (! aux[i]) {
                list.add(i);
            }
        }
        return list;
    }
}