/* 
In LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

Example 1:
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation: 
There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
Example 2:
Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
Output: 11
Explanation: 
The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.
Note:
There are at most 6 kinds of items, 100 special offers.
For each item, you need to buy at most 6 of them.
You are not allowed to buy more items than you want, even if that would lower the overall price.
 */

class Solution {
    private int res = 0;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int m = price.size();
        for (int i = 0; i < m; i++) {
            res += needs.get(i) * price.get(i);
        }
        int n = special.size();
        dfs(special, price, needs, m, n, 0, res);
        return res;
    }
    
    private void dfs(List<List<Integer>> special, List<Integer> price, List<Integer> needs, int m, int n, int cur, int curCost) {
        if (cur == n) {
            return;
        }
        boolean choose = true;
        List<Integer> sp = special.get(cur);
        List<Integer> newNeeds = new ArrayList<>();
        int oriCost = 0;
        for (int i = 0; i < m; i++) {
            int need = needs.get(i);
            int offer = sp.get(i);
            if (need < offer) {
                choose = false;
                break;
            }
            oriCost += offer * price.get(i);
            newNeeds.add(need - offer);
        }
        if (choose) {
            int newCost = curCost - oriCost + sp.get(m);
            res = Math.min(res, newCost);
            dfs(special, price, newNeeds, m, n, cur, newCost);
        }
        dfs(special, price, needs, m, n, cur + 1, curCost);
    }
}



class Solution {
    Integer[] dp;
    int kind;
    boolean overflow;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        kind = price.size();
        int[] need = new int[kind];
        for (int i = 0; i < kind; i++) {
            need[i] = needs.get(i);
        }
        int max = encode(need);
        dp = new Integer[max + 1];
        return dfs(special, price, need);
    }

    private int dfs(List<List<Integer>> special, List<Integer> price, int[] needs) {
        int id = encode(needs);
        if (dp[id] != null) return dp[id];
        if (id == 0) return 0;
        dp[id] = getSum(needs, price);
        for (List<Integer> p: special) {
            overflow = false;
            int[] next = getNext(needs, p);
            if (overflow) continue;
            dp[id] = Math.min(dp[id], dfs(special, price, next) + p.get(kind));
        }
        return dp[id];
    }

    private int getSum(int[] needs, List<Integer> price) {
        int sum = 0;
        for (int i = 0; i < kind; i++) {
            sum += needs[i] * price.get(i);
        }
        return sum;
    }

    private int[] getNext(int[] needs, List<Integer> p) {
        int[] next = new int[kind];
        for (int i = 0; i < kind; i++) {
            next[i] = needs[i] - p.get(i);
            if (next[i] < 0) {
                overflow = true;
                break;
            }
        }
        return next;
    }

    private int encode(int[] need) {
        int code = 0;
        for (int amount: need) {
            code <<= 3;
            code |= amount;
        }
        return code;
    }
}