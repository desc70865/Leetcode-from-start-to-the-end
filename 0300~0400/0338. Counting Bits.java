/* 
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */

class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        int m = 2;
        for (int i = 1; i <= num; i++) {
            if (i >= m * 2) {
                m *= 2;
            }
            System.out.println(m);
            res[i] = res[i - m] + 1;
        }
        return res;
    }
}



class Solution {
    public int[] countBits(int num) {
        int arr[] = new int[num + 1];
        helper(arr, 1, 1);
        return arr;
    }
    
    public void helper(int[] arr, int count, int num) {
        if (num >= arr.length) {
            return;
        }
        arr[num] = count;
        
        helper(arr, count, 2 * num);
        helper(arr, count + 1, 2 * num + 1);
    }
}