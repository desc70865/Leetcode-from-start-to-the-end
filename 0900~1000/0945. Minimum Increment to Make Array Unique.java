/* 
Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.

Return the least number of moves to make every value in A unique.

 

Example 1:

Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].
Example 2:

Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 

Note:

0 <= A.length <= 40000
0 <= A[i] < 40000
 */


/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/solution/hen-kuai-ao-by-keylol-gwic/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    int[] p;

    public int minIncrementForUnique(int[] A) {
        p = new int[80000];
        Arrays.fill(p, -1);
        int ans = 0;
        for (int a: A) ans += find(a) - a;
        return ans;
    }

    private int find(int x) {
        return p[x] == -1 ? p[x] = x : (p[x] = find(p[x] + 1));
    }
}



class Solution {
    public int minIncrementForUnique(int[] A) {
        int len = A.length;
        if (len < 2) return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a: A) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        int size = max - min;
        int[] bucket = new int[size + 1];
        for (int a: A) bucket[a - min]++;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            if (bucket[i] > 1) {
                ans += bucket[i] - 1;
                bucket[i + 1] += bucket[i] - 1;
            }
        }
        return ans + bucket[size] * (bucket[size] - 1) / 2;
    }
}