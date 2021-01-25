/* 
On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.

You are given an integer n, an array languages, and an array friendships where:

There are n languages numbered 1 through n,
languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 

Example 1:

Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
Output: 1
Explanation: You can either teach user 1 the second language or user 2 the first language.
Example 2:

Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
Output: 2
Explanation: Teach the third language to users 1 and 3, yielding two users to teach.
 

Constraints:

2 <= n <= 500
languages.length == m
1 <= m <= 500
1 <= languages[i].length <= n
1 <= languages[i][j] <= n
1 <= u​​​​​​i < v​​​​​​i <= languages.length
1 <= friendships.length <= 500
All tuples (u​​​​​i, v​​​​​​i) are unique
languages[i] contains only unique values
 */

class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        int k = friendships.length;
        List<BitSet> sets = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            BitSet curSet = new BitSet(n);
            for (int l: languages[i]) {
                curSet.set(l-1);
            }
            sets.add(curSet);
        }
        List<int[]> badEdges = new ArrayList<>();
        for (int[] f: friendships) {
            BitSet overlap = new BitSet(n);
            overlap.or(sets.get(f[0]-1));
            overlap.and(sets.get(f[1]-1));
            if (overlap.cardinality() == 0) {
                badEdges.add(f);
            }
        }
        int best = m;
        for (int l = 1; l <= n; l++) {
            Set<Integer> learn = new HashSet<>();
            for (int[] f: badEdges) {
                BitSet set1 = sets.get(f[0]-1);
                BitSet set2 = sets.get(f[1]-1);
                if (!set1.get(l-1)) {
                    learn.add(f[0]);
                }
                if (!set2.get(l-1)) {
                    learn.add(f[1]);
                }
            }
            best = Math.min(best, learn.size());
        }
        return best;
    }
}