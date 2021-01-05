/* 
On a table are N cards, with a positive integer printed on the front and back of each card (possibly different).

We flip any number of cards, and after we choose one card. 

If the number X on the back of the chosen card is not on the front of any card, then this number X is good.

What is the smallest number that is good?  If no number is good, output 0.

Here, fronts[i] and backs[i] represent the number on the front and back of card i. 

A flip swaps the front and back numbers, so the value on the front is now on the back and vice versa.

Example:

Input: fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
Output: 2
Explanation: If we flip the second card, the fronts are [1,3,4,4,7] and the backs are [1,2,4,1,3].
We choose the second card, which has number 2 on the back, and it isn't on the front of any card, so 2 is good.
 

Note:

1 <= fronts.length == backs.length <= 1000.
1 <= fronts[i] <= 2000.
1 <= backs[i] <= 2000.
 */

class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        int len = fronts.length;
        for (int i = 0; i < len; i++) {
            if (fronts[i] == backs[i]) {
                set.add(fronts[i]);
            } else {
                pq.offer(fronts[i]);
                pq.offer(backs[i]);
            }
        }
        while (! pq.isEmpty()) {
            if (set.contains(pq.peek())) {
                pq.poll();
            } else {
                return pq.poll();
            }
        }
        return 0;
    }
}



class Solution {
    int[] set;
    int max = 0;

    public int flipgame(int[] fronts, int[] backs) {
        set = new int[2001];
        int len = fronts.length;
        for (int i = 0; i < len; i++) {
            if (fronts[i] == backs[i]) {
                set[fronts[i]] = -1;
            } else {
                insert(fronts[i]);
                insert(backs[i]);
            }
        }
        for (int i = 1; i <= max; i++) {
            if (set[i] == 1) {
                return i;
            }
        }
        return 0;
    }

    private void insert(int x) {
        if (set[x] >= 0) {
            set[x] = 1;
            max = Math.max(max, x);
        }
    }
}