/* 
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
Example 4:

Input: asteroids = [-2,-1,1,2]
Output: [-2,-1,1,2]
Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 

Constraints:

1 <= asteroids <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int n: asteroids) {
            if (n > 0) {
                q.offerLast(n);
                continue;
            }
            while (! q.isEmpty() && q.peekLast() < -n) q.pollLast();
            if (q.isEmpty()) res.add(n);
            else if (q.peekLast() == -n) q.pollLast();
        }
        while (! q.isEmpty()) res.add(q.pollFirst());
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}



class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int N = asteroids.length;
        int[] res = new int[N];
        int idx = -1;
        for (int i = 0; i < N; i++) {
            if (idx < 0 || asteroids[i] > 0 || res[idx] < 0) res[++idx] = asteroids[i];
            else if (res[idx--] + asteroids[i] > 0) idx++;
            else if (res[idx + 1] + asteroids[i] < 0) i--;
        }
        return Arrays.copyOf(res, idx + 1);
    }
}