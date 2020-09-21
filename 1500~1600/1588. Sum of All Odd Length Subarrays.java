/* 
Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.

A subarray is a contiguous subsequence of the array.

Return the sum of all odd-length subarrays of arr.

 

Example 1:

Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
Example 2:

Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
Example 3:

Input: arr = [10,11,12]
Output: 66
 

Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 1000
 */


class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int N = arr.length;
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j += 2) {
                for (int k = i; k <= j; k++) res += arr[k];
            }
        }
        return res;
    }
}



class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int N = arr.length;
        int res = 0;
        int[] k = new int[N];
        k[0] = (N + 1) / 2;
        int t = k[0] - 1;
        for (int i = 1; i < (N + 1) / 2; i++) k[i] = k[i - 1] + t-- - (i % 2) * (N % 2);
        for (int i = (N + 1) / 2; i < N; i++) k[i] = k[N - 1 - i];
        for (int i = 0; i < N; i++) res += k[i] * arr[i];
        return res;
    }
}



class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int N = arr.length;
        int res = 0;
        int k = 0, t = (N + 1) / 2;
        for (int i = 0; i < N; i++) {
            k += t-- - (i % 2) * (N % 2);
            res += k * arr[i];
        }
        return res;
    }
}