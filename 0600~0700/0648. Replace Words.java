/* 
In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.

Return the sentence after the replacement.

 

Example 1:

Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Example 2:

Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"
Example 3:

Input: dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
Output: "a a a a a a a a bbb baba a"
Example 4:

Input: dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Example 5:

Input: dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
Output: "it is ab that this solution is ac"
 

Constraints:

1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters.
1 <= sentence.length <= 10^6
sentence consists of only lower-case letters and spaces.
The number of words in sentence is in the range [1, 1000]
The length of each word in sentence is in the range [1, 1000]
Each two consecutive words in sentence will be separated by exactly one space.
sentence does not have leading or trailing spaces.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/replace-words/solution/dong-de-du-dong-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    Set<String> prefix;

    public String replaceWords(List<String> dictionary, String sentence) {
        prefix = new HashSet<>();
        Collections.sort(dictionary);
        prefix.add(dictionary.get(0));
        for (int i = 1; i < dictionary.size(); i++) {
            String k = dictionary.get(i);
            if (check(k) == k.length()) {
                prefix.add(k);
            }
        }
        String[] str = sentence.split(" ");
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].substring(0, check(str[i]));
        }
        return String.join(" ", str);
    }

    private int check(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (prefix.contains(s.substring(0, i))) return i;
        }
        return s.length();
    }
}



class Solution {
    List<String> prefix;
    int len = 0;

    public String replaceWords(List<String> dictionary, String sentence) {
        prefix = new ArrayList<>();
        Collections.sort(dictionary);
        for (String k: dictionary) {
            if (len > 0 && k.startsWith(prefix.get(len - 1))) continue;
            prefix.add(k);
            len++;
        }
        StringBuilder res = new StringBuilder();
        for (String s: sentence.split(" ")) {
            String pre = prefix.get(binarySearch(s));
            if (s.startsWith(pre)) res.append(pre).append(" ");
            else res.append(s).append(" ");
        }
        res.setLength(res.length() - 1);
        return res.toString();
    }

    private int binarySearch(String s) {
        int L = 0;
        int R = len - 1;
        while (L < R) {
            int M = L + R >> 1;
            if (s.startsWith(prefix.get(M))) return M;
            else if (prefix.get(M).compareTo(s) < 0) L = M + 1;
            else R = M - 1;
        }
        return L;
    }
}



class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie prefix = new Trie();
        for (String word: dictionary) prefix.insert(word);
        StringBuilder res = new StringBuilder();
        for (String word: sentence.split(" ")) res.append(prefix.search(word)).append(" ");
        res.setLength(res.length() - 1);
        return res.toString();
    }
}

class Trie {
    Trie[] next;
    String word;

    public Trie() {
        next = new Trie[26];
    }

    public void insert(String word) {
        Trie node = this;
        for (char c: word.toCharArray()) {
            if (node.next[c - 'a'] == null)
                node.next[c - 'a'] = new Trie();
            node = node.next[c - 'a'];
            // if (node.word != null) return;
        }
        node.word = word;
    }

    public String search(String word) {
        Trie node = this;
        for (char c: word.toCharArray()) {
            if (node.next[c - 'a'] == null) return word;
            node = node.next[c - 'a'];
            if (node.word != null)
                return node.word;
        }
        return word;
    }
}