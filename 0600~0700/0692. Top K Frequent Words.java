/* 
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            map.merge(word, 1, Integer::sum);
        }
        List<Node> list = new ArrayList<>();
        for (String key: map.keySet()) {
            list.add(new Node(key, map.get(key)));
        }
        Collections.sort(list, (a, b) -> a.t == b.t ? a.s.compareTo(b.s) : b.t - a.t);
        List<String> res = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).s);
        }
        return res;
    }
}

class Node {
    String s;
    int t;

    public Node(String s, int t) {
        this.s = s;
        this.t = t;
    }
}