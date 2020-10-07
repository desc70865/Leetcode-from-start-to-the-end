/* 
Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.

Formally, we can partition the array if we can find indexes i+1 < j with (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])

 

Example 1:

Input: A = [0,2,1,-6,6,-7,9,1,2,0,1]
Output: true
Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
Example 2:

Input: A = [0,2,1,-6,6,7,9,-1,2,0,1]
Output: false
Example 3:

Input: A = [3,3,6,5,-2,2,5,1,-9,4]
Output: true
Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 

Constraints:

3 <= A.length <= 50000
-10^4 <= A[i] <= 10^4
 */

class Solution {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int num: A) sum += num;
        if (sum % 3 != 0) return false;
        int N = A.length;
        sum /= 3;
        int l = 1, r = N - 2;
        int a = sum - A[0], b = sum - A[N - 1];
        while (l < r && a != 0) a -= A[l++];
        while (l < r && b != 0) b -= A[r--];
        return a == 0 && b == 0;
    }
}