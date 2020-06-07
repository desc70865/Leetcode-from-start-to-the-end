/* 
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
 */

class Solution {
    public static String getPermutation(int n, int k) {
        int[] totals = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    	StringBuilder ans = new StringBuilder();
        ArrayList<String> s = new ArrayList<String>();
    	Collections.addAll(s,"0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        int total = totals[n]; // 当前循环长度
        int index; // 当前循环序数
        while (n > 1) {
            total /= n;
            index = (k-1) / total + 1;
            ans.append(s.remove(index));
            k = (k-1) % total + 1;
            n--;
            System.out.println(ans);
            System.out.println("total = " + total);
            System.out.println("index = " + index);
            System.out.println("k = " + k);
            System.out.println("n = " + n);
            System.out.println("--------------------------");
        }
        return ans.append(s.remove(1)).toString();
    }
}

// 别忘了去掉测试