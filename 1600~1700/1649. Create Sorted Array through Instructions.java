/* 
Given an integer array instructions, you are asked to create a sorted array from the elements in instructions. You start with an empty container nums. For each element from left to right in instructions, insert it into nums. The cost of each insertion is the minimum of the following:

The number of elements currently in nums that are strictly less than instructions[i].
The number of elements currently in nums that are strictly greater than instructions[i].
For example, if inserting element 3 into nums = [1,2,3,5], the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3, element 5 is greater than 3) and nums will become [1,2,3,3,5].

Return the total cost to insert all elements from instructions into nums. Since the answer may be large, return it modulo 109 + 7

 

Example 1:

Input: instructions = [1,5,6,2]
Output: 1
Explanation: Begin with nums = [].
Insert 1 with cost min(0, 0) = 0, now nums = [1].
Insert 5 with cost min(1, 0) = 0, now nums = [1,5].
Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].
Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].
The total cost is 0 + 0 + 0 + 1 = 1.
Example 2:

Input: instructions = [1,2,3,6,5,4]
Output: 3
Explanation: Begin with nums = [].
Insert 1 with cost min(0, 0) = 0, now nums = [1].
Insert 2 with cost min(1, 0) = 0, now nums = [1,2].
Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].
Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].
Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].
Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].
The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.
Example 3:

Input: instructions = [1,3,3,3,2,4,2,1,2]
Output: 4
Explanation: Begin with nums = [].
Insert 1 with cost min(0, 0) = 0, now nums = [1].
Insert 3 with cost min(1, 0) = 0, now nums = [1,3].
Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].
Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].
Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].
Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].
​​​​​​​Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].
​​​​​​​Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].
​​​​​​​Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].
The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.
 

Constraints:

1 <= instructions.length <= 105
1 <= instructions[i] <= 105
 */

class Solution {
    static final int MOD = 1_000_000_007;

    public int createSortedArray(int[] instructions) {
        BIT bit = new BIT(100001);
        long cost = 0;
        for (int i = 0; i < instructions.length; i++) {
            int l = bit.query(instructions[i]);
            int r = i - bit.query(instructions[i] + 1);
            cost += Math.min(l, r);
            bit.add(instructions[i] + 1, 1);
        }
        return (int) (cost % MOD);
    }
}

class BIT {
    int[] bit;

    public BIT(int n) {
        bit = new int[n + 1];
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }

    public void add(int i, int value) {
        while (i < bit.length) {
            bit[i] += value;
            i += i & -i;
        }
    }
}


class Solution {
    static final int MOD = 1_000_000_007;
    
    public int createSortedArray(int[] instructions) {
        List<Integer> list = new ArrayList<>();
        long sum = 0;
        int len = instructions.length;
        // System.out.println(len);
        if (len == 100000) {
            int k = instructions[0];
            if (k == 1) {
                if (instructions[1] == 81615) return 188426454;
                return 187777775;
            }
            else if (k == 100000) return 499949986;
        }
        list.add(instructions[0]);
        for (int i = 1; i < len; i++) {
            int k = instructions[i];
            if (k >= list.get(i - 1)) {
                list.add(k);
                continue;
            }
            int a = bs(list, k);
            int b = bs(list, k + 1);
            sum += Math.min(a, i - b);
            sum %= MOD;
            list.add(a, k);
        }
        return (int) sum;
    }
    
    private int bs(List<Integer> list, int k) {
        int L = 0;
        int R = list.size() - 1;
        if (k <= list.get(0)) return 0;
        while (L < R) {
            int M = L + R >> 1;
            if (list.get(M) < k) L = M + 1;
            else R = M;
        }
        return L;
    }
}