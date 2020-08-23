/* 
Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

 

Example 1:

Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 

Note:

1 <= A.length <= 5000
0 <= A[i] <= 5000
 */

class Solution {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            while (i < j && A[i] % 2 == 0) {
                i++;
            }
            while (i < j && A[j] % 2 == 1) {
                j--;
            }
            swap(A, i, j);
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
    public int[] sortArrayByParity(int[] A) {
        int l = 0, r = A.length-1;
        while (l < r) {
            while (l < r && A[l] % 2 == 0) l++;
            while (l < r && A[r] % 2 == 1) r--;
            if (l < r) swap(A, l, r);
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}



class Solution {
    public int[] sortArrayByParity(int[] A) {
        int N = A.length, l = 0, r = N - 1;
        int[] B = new int[N];
        for (int num: A) {
            if (num % 2 == 0) B[l++] = num;
            else B[r--] = num;
        }
        return B;
    }
}