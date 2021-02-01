/* 
Given two numbers arr1 and arr2 in base -2, return the result of adding them together.

Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array, format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.

Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.

 

Example 1:

Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
Output: [1,0,0,0,0]
Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.
Example 2:

Input: arr1 = [0], arr2 = [0]
Output: [0]
Example 3:

Input: arr1 = [0], arr2 = [1]
Output: [1]
 

Constraints:

1 <= arr1.length, arr2.length <= 1000
arr1[i] and arr2[i] are 0 or 1
arr1 and arr2 have no leading zeros
 */

class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        if (arr1.length < arr2.length) {
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        int[] aux = new int[arr1.length];
        int index = 0;
        for (int i = arr1.length - arr2.length; i < arr1.length; i++) {
            aux[i] = arr2[index++];
        }
        arr2 = aux;
        int carry = 0;
        for (int i = arr1.length - 1; i >= 0; i--) {
            if (arr1[i] + arr2[i] + carry >= 2) {
                arr1[i] = arr1[i] + arr2[i] + carry - 2;
                carry = -1;
                continue;
            }
            if (arr1[i] + arr2[i] + carry == -1) {
                arr1[i] = 1;
                carry = 1;
                continue;
            }
            arr1[i] += arr2[i] + carry;
            carry = 0;
        }
        if (carry == 0) {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == 1) {
                    int[] res = new int[arr1.length - i];
                    System.arraycopy(arr1, i, res, 0, res.length);
                    return res;
                }
            }
            return new int[]{0};
        } else {
            int[] res = new int[arr1.length + 2];
            res[0] = 1;
            res[1] = 1;
            System.arraycopy(arr1, 0, res, 2, arr1.length);
            return res;
        }
    }
}