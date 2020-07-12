/* 
Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.

 

Example 1:

Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
 

Constraints:

1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5
 */

class Solution {
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for (int i = arr.length-1; i >= 0; i--) {
            int dummy = arr[i];
            arr[i] = max;
            max = Math.max(dummy, max);
        }
        return arr;
    }
}



class Solution {
    public int[] replaceElements(int[] arr) {
        int N = arr.length;
        int[] B = new int[N];
        int max = -1;
        
        for (int i = N-1; i >= 0; i--) {
            B[i] = max;
            max = Math.max(max, arr[i]);
        }
        
        return B;
    }
}



class Solution {
    public int[] replaceElements(int[] arr) {
        replace(arr, arr.length-1, -1);
        return arr;
    }
    
    private void replace(int[] arr, int ind, int val) {
        int temp = arr[ind];
        arr[ind] = val;
        if(ind == 0) {
            return;
        }
        replace(arr, --ind, Math.max(val, temp));
    }
}