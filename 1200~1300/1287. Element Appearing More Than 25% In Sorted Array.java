/* 
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.

 

Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
 

Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
 */

class Solution {
    public int findSpecialInteger(int[] arr) {
        int N = arr.length;
        int p = N / 4;
        for (int i = 0; i < N - p; i++) {
            if (arr[i] == arr[i + p]) return arr[i];
        }
        return -1;
    }
}



class Solution {
    int N;
    int S;
    int[] A;
    public int findSpecialInteger(int[] arr) {
        A = arr;
        N = arr.length;
        S = N / 4;
        for (int i = 0; i < N - S; i += S) {
            if (binarySearch(arr[i]) - binarySearch(arr[i] - 1) > S) return arr[i];
        }
        return -1;
    }

    private int binarySearch(int x) {
        int l = 0, r = N - 1;
        while (l <= r) {
            int mid = l + r >> 1;
            if (A[mid] > x) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }
}