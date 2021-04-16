/* 
Bob is standing at cell (0, 0), and he wants to reach destination: (row, column). He can only travel right and down. You are going to help Bob by providing instructions for him to reach destination.

The instructions are represented as a string, where each character is either:

'H', meaning move horizontally (go right), or
'V', meaning move vertically (go down).
Multiple instructions will lead Bob to destination. For example, if destination is (2, 3), both "HHHVV" and "HVHVH" are valid instructions.

However, Bob is very picky. Bob has a lucky number k, and he wants the kth lexicographically smallest instructions that will lead him to destination. k is 1-indexed.

Given an integer array destination and an integer k, return the kth lexicographically smallest instructions that will take Bob to destination.

 

Example 1:



Input: destination = [2,3], k = 1
Output: "HHHVV"
Explanation: All the instructions that reach (2, 3) in lexicographic order are as follows:
["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
Example 2:



Input: destination = [2,3], k = 2
Output: "HHVHV"
Example 3:



Input: destination = [2,3], k = 3
Output: "HHVVH"
 

Constraints:

destination.length == 2
1 <= row, column <= 15
1 <= k <= nCr(row + column, row), where nCr(a, b) denotes a choose b​​​​​.
 */

class Solution {
    int[][] nCr;
    public String kthSmallestPath(int[] destination, int k) {
        nCr = new int[30][16];
        return dfs(destination[1], destination[0], k).toString();
    }

    private StringBuilder dfs(int H, int V, int k) {
        StringBuilder sb = new StringBuilder();
        if (H == 0 && V == 0) ;
        else if (H == 0) {
            sb.append('V').append(dfs(H, V - 1, k));
        } else if (V == 0) {
            sb.append('H').append(dfs(H - 1, V, k));
        } else {
            int threshold = nCr(H - 1 + V, V);
            if (k <= threshold) {
                sb.append('H').append(dfs(H - 1, V, k));
            } else {
                sb.append('V').append(dfs(H, V - 1, k - threshold));
            }
        }
        return sb;
    }

    private int nCr(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (nCr[n][r] > 0) return nCr[n][r];
        return nCr[n][r] = nCr(n - 1, r) + nCr(n - 1, r - 1);
    }
}