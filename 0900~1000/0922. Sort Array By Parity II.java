/* 
Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.

 

Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 

Note:

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000
 */

class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int N = A.length;
        int i = 0, j = N - 1;
        while (i < N && j > 0) {
            while (i < N && A[i] % 2 == 0) {
                i += 2;
            }
            while (j > 0 && A[j] % 2 == 1) {
                j -= 2;
            }
            if (i < N && j > 0) {
                swap(A, i, j);
            }
        }
        return A;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}



class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int evn = 0, odd = 1;
        while (evn < A.length && odd < A.length) {
            if ((A[evn] & 1) != 0) {
                while ((A[odd] & 1) == 1) {
                    odd += 2;
                }
                int tmp = A[evn];
                A[evn] = A[odd];
                A[odd] = tmp;
                odd += 2;
            } else {
                evn += 2;
            }
        }
        return A;
    }
}