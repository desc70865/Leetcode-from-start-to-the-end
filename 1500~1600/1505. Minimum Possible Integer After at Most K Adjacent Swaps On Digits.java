/* 
Given a string num representing the digits of a very large integer and an integer k.

You are allowed to swap any two adjacent digits of the integer at most k times.

Return the minimum integer you can obtain also as a string.

 

Example 1:


Input: num = "4321", k = 4
Output: "1342"
Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
Example 2:

Input: num = "100", k = 1
Output: "010"
Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
Example 3:

Input: num = "36789", k = 1000
Output: "36789"
Explanation: We can keep the number without any swaps.
Example 4:

Input: num = "22", k = 22
Output: "22"
Example 5:

Input: num = "9438957234785635408", k = 23
Output: "0345989723478563548"
 

Constraints:

1 <= num.length <= 30000
num contains digits only and doesn't have leading zeros.
1 <= k <= 10^9
 */

class Solution {
    public String minInteger(String num, int k) {
        char[] s = num.toCharArray();
        int n = s.length;
        List<Integer>[] list = new List[10];
        for (int i = 0; i < 10; ++i) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            list[s[i] - '0'].add(i);
        }
        int[] next = new int[10];
        boolean[] u = new boolean[n];
        char[] ans = new char[n];
        int p = -1;
        FenwichTree fwt = new FenwichTree(n);
        for (int i = 0; i < n; ++i) {
            if (u[i]) continue;
            int max = s[i] - '0';
            int x = 0;
            for (; x < max; ++x) {
                while (next[x] < list[x].size() && list[x].get(next[x]) < i) {
                    next[x]++;
                }
                if (next[x] == list[x].size()) continue;
                int idx = list[x].get(next[x]);
                int cost = idx - fwt.sumRange(0, idx - 1);
                if (cost <= k) {
                    k -= cost;
                    ++next[x];
                    u[idx] = true;
                    fwt.update(idx, 1);
                    ans[++p] = (char) (x + '0');
                    break;
                }
            }
            if (x < max) {
                --i;
                continue;
            }
            u[i] = true;
            fwt.update(i, 1);
            ans[++p] = (char) (max + '0');
        }
        return new String(ans);
    }
}

// RMQ(Fenwich Tree)
class FenwichTree {
    int n;
    int[] nums;
    int[] sums;

    public FenwichTree(int n) {
        this.n = n;
        this.nums = new int[n];
        this.sums = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            updateBit(i + 1, nums[i]);
        }
    }

    public void update(int i, int val) {
        updateBit(i + 1, val - nums[i]);
        nums[i] = val;
    }

    private void updateBit(int i, int diff) {
        while (i <= n) {
            sums[i] += diff;
            i += lowBit(i);
        }
    }

    public int sumRange(int i, int x) {
        return preSum(x + 1) - preSum(i);
    }

    private int preSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += sums[i];
            i -= lowBit(i);
        }
        return sum;
    }

    private int lowBit(int i) {
        return i & (-i);
    }
}



class Solution {
    static class ListNode {
        int index;
        ListNode next;

        ListNode(int index, ListNode next) {
            this.index = index;
            this.next = next;
        }
    }

    public String minInteger(String num, int k) {
        char[] s = num.toCharArray();
        int n = s.length;
        ListNode[] list = new ListNode[10];
        for (int i = n - 1; i >= 0; --i) {
            int digit = s[i] - '0';
            list[digit] = new ListNode(i, list[digit]);
        }
        char[] ans = new char[n];
        int[] bits = new int[n + 1];
        int i = 0, j = 0;
        for (; i < n && k > 0; ++i) {
            for (int l = j; ; ++l) {
                if (list[l] == null) {
                    if (l == j) {
                        j++;
                    }
                    continue;
                }
                int index = list[l].index;
                int cost = index + query(bits, index) - i;
                if (cost <= k) {
                    list[l] = list[l].next;
                    ans[i] = (char) (l + '0');
                    update(bits, index);
                    k -= cost;
                    break;
                }
            }
        }
        if (i == n) {
            return new String(ans);
        }
        boolean[] unused = new boolean[n];
        for (; j < list.length; ++j) {
            for (ListNode node = list[j]; node != null; node = node.next) {
                unused[node.index] = true;
            }
        }
        for (int l = 0; l < n; ++l) {
            if (unused[l]) {
                ans[i++] = s[l];
            }
        }
        return new String(ans);
    }

    private int query(int[] bits, int i) {
        int sum = 0;
        for (++i; i < bits.length; i += i & -i) {
            sum += bits[i];
        }
        return sum;
    }

    private void update(int[] bits, int i) {
        for (++i; i > 0; i -= i & -i) {
            bits[i]++;
        }
    }
}