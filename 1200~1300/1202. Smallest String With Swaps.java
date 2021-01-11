/* 
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.

 

Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination: 
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"
 

Constraints:

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
 */

class Solution {
    int[] p;
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        p = new int[len];
        for (int i = 0; i < len; i++) {
            p[i] = i;
        }
        for (List<Integer> pair: pairs) {
            union(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < len; i++) {
            p[i] = find(i);
        }
        // System.out.println(Arrays.toString(p));
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.computeIfAbsent(p[i], z -> new PriorityQueue<>()).offer(chs[i]);
        }
        for (int i = 0; i < len; i++) {
            chs[i] = map.get(p[i]).poll();
        }
        return new String(chs);
    }
    
    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b);
        else if (a < b) {
            p[b] = a;
        } else {
            p[a] = b;
        }
    }
    
    private int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }
}