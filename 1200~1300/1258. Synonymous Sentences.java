/* 
Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences sorted lexicographically.
 

Example 1:

Input:
synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
text = "I am happy today but was sad yesterday"
Output:
["I am cheerful today but was sad yesterday",
"I am cheerful today but was sorrow yesterday",
"I am happy today but was sad yesterday",
"I am happy today but was sorrow yesterday",
"I am joy today but was sad yesterday",
"I am joy today but was sorrow yesterday"]
Example 2:

Input: synonyms = [["happy","joy"],["cheerful","glad"]], text = "I am happy today but was sad yesterday"
Output: ["I am happy today but was sad yesterday","I am joy today but was sad yesterday"]
Example 3:

Input: synonyms = [["a","b"],["c","d"],["e","f"]], text = "a c e"
Output: ["a c e","a c f","a d e","a d f","b c e","b c f","b d e","b d f"]
Example 4:

Input: synonyms = [["a","QrbCl"]], text = "d QrbCl ya ya NjZQ"
Output: ["d QrbCl ya ya NjZQ","d a ya ya NjZQ"]
 

Constraints:

0 <= synonyms.length <= 10
synonyms[i].length == 2
synonyms[i][0] != synonyms[i][1]
All words consist of at most 10 English letters only.
text is a single space separated sentence of at most 10 words.
 */

class Solution {
    int idx = 0;
    int max = 0;

    UnionFind uf;
    Map<String, Integer> index = new HashMap<>();
    Map<Integer, Set<String>> dict = new HashMap<>();
    List<String> res = new ArrayList<>();

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        max = synonyms.size() * 2;
        uf = new UnionFind(max + 1);
        for (List<String> pair: synonyms) {
            uf.union(mapping(pair.get(0)), mapping(pair.get(1)));
        }
        for (int i = 0; i <= max; i++) {
            uf.find(i);
        }
        for (String k: index.keySet()) {
            dict.computeIfAbsent(uf.p[index.get(k)], z -> new TreeSet<>()).add(k);
        }
        String[] str = text.split(" ");
        dfs(str, 0, new String[str.length]);
        return res;
    }

    private void dfs(String[] str, int idx, String[] ans) {
        if (idx == str.length) {
            res.add(String.join(" ", ans));
            return;
        }
        int cur = uf.p[index.getOrDefault(str[idx], max)];
        if (! dict.containsKey(cur)) {
            ans[idx] = str[idx];
            dfs(str, idx + 1, ans);
            return;
        }
        for (String item: dict.get(cur)) {
            ans[idx] = item;
            dfs(str, idx + 1, ans);
        }
    }

    private int mapping(String s) {
        return index.getOrDefault(s, index.putIfAbsent(s, idx++));
    }
}

class UnionFind {
    int[] p;

    public UnionFind(int n) {
        p = new int[n];
        for (int i = 1; i < n; i++) {
            p[i] = i;
        }
    }

    public int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) ;
        else {
            p[a] = b;
        }
    }
}