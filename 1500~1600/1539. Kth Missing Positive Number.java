/* 
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.

 

Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length
 */

class Solution {
    public int findKthPositive(int[] arr, int k) {
        if (arr[0] > k) return k;
        k -= arr[0] - 1;
        int p = 0, len = arr.length;
        for (int i = 1; i < len; i++) {
            if ((p = arr[i] - arr[i-1] - 1) >= k) return arr[i-1] + k;
            else k -= p;
        }
        return arr[len - 1] + k;
    }
}



class Solution {
   public int findKthPositive(int[] arr, int k) {
		int n = 1, i = 0;
		while (i < arr.length) {
			if (n < arr[i])   k--;
			else if (n == arr[i]) i++;
			if (k == 0) return n;
			n++;
		}
		return n+k-1;
	}
}