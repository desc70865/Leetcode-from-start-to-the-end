/* 
Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.

The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.

 

Example 1:

Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
Output: 2
Explanation: 
For arr1[0]=4 we have: 
|4-10|=6 > d=2 
|4-9|=5 > d=2 
|4-1|=3 > d=2 
|4-8|=4 > d=2 
For arr1[1]=5 we have: 
|5-10|=5 > d=2 
|5-9|=4 > d=2 
|5-1|=4 > d=2 
|5-8|=3 > d=2
For arr1[2]=8 we have:
|8-10|=2 <= d=2
|8-9|=1 <= d=2
|8-1|=7 > d=2
|8-8|=0 <= d=2
Example 2:

Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
Output: 2
Example 3:

Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
Output: 1
 

Constraints:

1 <= arr1.length, arr2.length <= 500
-10^3 <= arr1[i], arr2[j] <= 10^3
0 <= d <= 100
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/find-the-distance-value-between-two-arrays/solution/yi-chong-huan-xing-de-jie-fa-by-keylol-hxlh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int[] map = new int[2002];
        for (int num: arr2) {
            map[Math.max(0, num - d + 1000)]++;
            map[Math.min(2001, num + d + 1001)]--;
        }
        for (int i = 1; i <= 2001; i++) {
            map[i] += map[i - 1];
        }
        int ans = 0;
        for (int num: arr1) {
            if (map[num + 1000] == 0) ans++;
        }
        return ans;
    }
}