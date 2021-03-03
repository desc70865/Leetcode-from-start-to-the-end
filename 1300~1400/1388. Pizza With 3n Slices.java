/* 
There is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:

You will pick any pizza slice.
Your friend Alice will pick next slice in anti clockwise direction of your pick. 
Your friend Bob will pick next slice in clockwise direction of your pick.
Repeat until there are no more slices of pizzas.
Sizes of Pizza slices is represented by circular array slices in clockwise direction.

Return the maximum possible sum of slice sizes which you can have.

 

Example 1:



Input: slices = [1,2,3,4,5,6]
Output: 10
Explanation: Pick pizza slice of size 4, Alice and Bob will pick slices with size 3 and 5 respectively. Then Pick slices with size 6, finally Alice and Bob will pick slice of size 2 and 1 respectively. Total = 4 + 6.
Example 2:



Input: slices = [8,9,8,6,1,1]
Output: 16
Output: Pick pizza slice of size 8 in each turn. If you pick slice with size 9 your partners will pick slices of size 8.
Example 3:

Input: slices = [4,1,2,5,8,3,1,9,7]
Output: 21
Example 4:

Input: slices = [3,1,2]
Output: 3
 

Constraints:

1 <= slices.length <= 500
slices.length % 3 == 0
1 <= slices[i] <= 1000
 */

class Solution {
    public int maxSizeSlices(int[] slices) {
        return Math.max(max(slices, 0), max(slices, 1));
    }

    public int max(int[] arr, int base) {
        int size = arr.length;
        int trisection = (size) / 3;
        int[][] dp = new int[size][trisection + 1];
        for (int L = 1; L < size; L++) {
            for (int R = 1; R <= trisection; R++) {
                dp[L][R] = Math.max(dp[L - 1][R], (L >= 2 ? dp[L - 2][R - 1] : 0) + arr[L - 1 + base]);
            }
        }
        return dp[size - 1][trisection];
    }
}



class Solution {
    public int maxSizeSlices(int[] slices) {
        int len = slices.length;
        int[] L = new int[len];
        int[] R = new int[len];
        for (int i = 0; i < len; i++) {
            L[i] = i == 0 ? len - 1 : i - 1;
            R[i] = i == len - 1 ? 0 : i + 1;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        for (int i = 0; i < len; i++) {
            pq.offer(new int[]{slices[i], i});
        }
        boolean[] v = new boolean[len];
        int ans = 0;
        for (int i = 0; i < len / 3; i++) {
            while (v[pq.peek()[1]]) {
                pq.poll();
            }
            int pos = pq.poll()[1];
            ans += slices[pos];
            slices[pos] = slices[L[pos]] + slices[R[pos]] - slices[pos];
            pq.offer(new int[]{slices[pos], pos});
            v[L[pos]] = v[R[pos]] = true;
            R[L[L[pos]]] = pos;
            L[R[R[pos]]] = pos;
            L[pos] = L[L[pos]];
            R[pos] = R[R[pos]];
        }
        return ans;
    }
}