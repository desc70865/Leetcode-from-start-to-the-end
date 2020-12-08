/* 
Given an integer array arr, return the length of a maximum size turbulent subarray of arr.

A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:

For i <= k < j:
arr[k] > arr[k + 1] when k is odd, and
arr[k] < arr[k + 1] when k is even.
Or, for i <= k < j:
arr[k] > arr[k + 1] when k is even, and
arr[k] < arr[k + 1] when k is odd.
 

Example 1:

Input: arr = [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
Example 2:

Input: arr = [4,8,12,16]
Output: 2
Example 3:

Input: arr = [100]
Output: 1
 

Constraints:

1 <= arr.length <= 4 * 104
0 <= arr[i] <= 109
 */

class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if (len == 1) return 1;
        int[] aux = new int[len];
        for (int i = 1; i < len; i++) {
            if (arr[i] == arr[i - 1]) {
                aux[i] = 0;
            } else if (arr[i] > arr[i - 1]) {
                aux[i] = 1;
            } else {
                aux[i] = -1;
            }
        }
        // System.out.println(Arrays.toString(aux));
        int max = 1;
        int size = 0;
        for (int i = 0; i < len; i++) {
            if (aux[i] == 0 && i > 0 && aux[i - 1] == 0) {
                continue;
            }
            if (aux[i] == 0 || i == 0 || aux[i] + aux[i - 1] != 0) {
                max = Math.max(max, size + 1);
                size = 1;
            } else {
                size++;
            }
        }
        if (aux[len - 1] != 0) {
            max = Math.max(max, size + 1);
        }
        return max;
    }
}