/* 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

class Solution {
    public int maxProfit(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length ==0) return 0;
        int buy1 = Integer.MAX_VALUE;
        int buy2 = Integer.MAX_VALUE;
        int sell1 = 0; int sell2 = 0;
        for (int price : prices) {
            buy1 = Math.min(buy1, price);
            sell1 = Math.max(sell1, price - buy1);
            buy2 = Math.min(buy2, price-sell1);
            sell2 = Math.max(sell2, price-buy2);
        }
        return sell2;
    }
}



class Solution {
    private int sum, len, k;
    private List<Integer> list;
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        list = new ArrayList<>();
        sum = 0; len = 0; k = 2;
        for (int i=1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) sum += prices[i] - prices[i-1];
            else if (sum > 0) {
                insert();
            }
        }
        if (sum > 0) insert();
        int res = 0;
        for (int j=0; j < k && j < len; j++) {
            res += list.get(j);
        }
        return res;
    }
    
    private void insert() {
        if (len > 0 && list.get(len-1) > sum || len >= k && list.get(k-1) > sum || len == 0)
            list.add(sum);
        else {
            int start = 0, end = Math.min(len, k);
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (sum < list.get(mid)) start = mid+1;
                else if (sum >= list.get(mid)) end = mid-1;
            }
            list.add(start, sum);
        }
        len++;
        sum = 0;
    }
}