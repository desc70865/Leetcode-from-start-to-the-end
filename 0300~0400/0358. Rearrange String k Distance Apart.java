/* 
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc" 
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: "" 
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.
 */

class Solution {
    public String rearrangeString(String s, int k) {
        if (s == null) return "";
        if (k <= 1) return s;
        int[] map = new int[26];
        for (char c: s.toCharArray()) {
            map[c - 97]++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map[b] - map[a]);
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                pq.add(i);
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            int cur = pq.poll();
            queue.offer(cur);
            map[cur]--;
            sb.append((char) ('a' + cur));
            if (queue.size() >= k) {
                int peek = queue.poll();
                if (map[peek] > 0) {
                    pq.offer(peek);
                }
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}