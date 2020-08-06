/* 
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */

class WordDictionary {

    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */



class WordDictionary {
    private Map<Integer, List<String>> map;
    private boolean cmp(String s1, String s2) {
        for(int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if(ch1 == '.')  continue;
            else if(ch1 != ch2) return false;
        }
        return true;
    }
    /** Initialize your data structure here. */
    public WordDictionary() {
        map = new HashMap<Integer, List<String>>();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        int len = word.length();
        List<String> list = map.getOrDefault(len, new ArrayList<String>());
        list.add(word);
        map.put(len, list);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        int len = word.length();
        if (! map.containsKey(len)) return false;
        List<String> list = map.get(len);
        for (String str: list) {
            if (cmp(word, str)) return true;
        }
        return false;
    }
}