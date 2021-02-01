/* 
In an array A of 0s and 1s, how many non-empty subarrays have sum S?

 

Example 1:

Input: A = [1,0,1,0,1], S = 2
Output: 4
Explanation: 
The 4 subarrays are bolded below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
 

Note:

A.length <= 30000
0 <= S <= A.length
A[i] is either 0 or 1.
 */

class Solution {
    public int numSubarraysWithSum(int[] A, int S) {
        List<Integer> list = new ArrayList<>();
        int cnt = 0, sum = 0;
        for (int num: A) {
            if (num == 0) {
                cnt++;
            } else {
                if (S == 0) {
                    sum += cnt * (cnt + 1) / 2;
                } else  {
                    list.add(cnt);
                }
                cnt = 0;
            }
        }
        if (S == 0) {
            sum += cnt * (cnt + 1) / 2;
            return sum;
        } else {
            list.add(cnt);
        }
        for (int i = 0; i < list.size() - S; i++) {
            sum += (1 + list.get(i)) * (1 + list.get(i + S));
        }
        return sum;
    }
}