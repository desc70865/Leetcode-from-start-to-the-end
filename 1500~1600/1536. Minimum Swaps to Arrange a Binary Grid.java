/* 
Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.

A grid is said to be valid if all the cells above the main diagonal are zeros.

Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.

The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).

 

Example 1:


Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
Output: 3
Example 2:


Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
Output: -1
Explanation: All rows are similar, swaps have no effect on the grid.
Example 3:


Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
Output: 0
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 200
grid[i][j] is 0 or 1
 */

/*
class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/minimum-swaps-to-arrange-a-binary-grid/solution/lian-biao-shi-xian-by-keylol-b0h2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    int len = 0;
    int cnt = 0;

    public int minSwaps(int[][] grid) {
        this.len = grid.length;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < len; i++) {
            cur.next = new ListNode(sum(grid[i]));
            cur = cur.next;
        }
        for (int i = len - 1; i > 0; i--) {
            if (bubble(dummy, i)) return -1;
        }
        return cnt;
    }

    private boolean bubble(ListNode node, int threshold) {
        while (node.next != null) {
            if (node.next.val >= threshold) {
                node.next = node.next.next;
                return false;
            } else {
                node = node.next;
                cnt++;
            }
        }
        return true;
    }

    private int sum(int[] nums) {
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] == 1) {
                break;
            } else {
                sum++;
            }
        }
        return sum;
    }
}



class Solution {
    public int minSwaps(int[][] grid) {
        int len = grid.length;
        int[] cnt = new int[len];
        for (int i = 0; i < len; i++) {
            int n = 0;
            for (int j = len-1; j >= 0; j--) {
                if (grid[i][j] == 0) n++;
                else break;
            }
            cnt[i] = n;
        }
        return helper(cnt);
    }
    
    private int helper(int[] arr) {
        int len = arr.length - 1, res = 0;
        outer:
        for (int i = 0; i <= len; i++) {
            for (int j = i; j <= len; j++) {
                if (arr[j] < len - i) continue;
                for (int k = j-1; k >= i; k--) {
                    swap(arr, k, k+1);
                    res++;
                }
                continue outer;
            }
            return -1;
        }
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}