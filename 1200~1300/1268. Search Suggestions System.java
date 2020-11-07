/* 
Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed. 

 

Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
Example 4:

Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]
 

Constraints:

1 <= products.length <= 1000
There are no repeated elements in products.
1 <= Σ products[i].length <= 2 * 10^4
All characters of products[i] are lower-case English letters.
1 <= searchWord.length <= 1000
All characters of searchWord are lower-case English letters.
 */

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            String cur = searchWord.substring(0, i);
            List<String> tmp = new ArrayList<>();
            for (String s: products) {
                if (s.startsWith(cur)) {
                    tmp.add(s);
                    if (tmp.size() == 3) break;
                } else if (tmp.size() > 0) break;
            }
            res.add(tmp);
        }
        return res;
    }
}



class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            String cur = searchWord.substring(0, i);
            List<String> tmp = new ArrayList<>();
            for (int j = bs(products, cur), k = 0; j < products.length && k < 3; j++, k++) {
                if (! products[j].startsWith(cur)) break;
                tmp.add(products[j]);
            }
            res.add(tmp);
        }
        return res;
    }

    private int bs(String[] products, String s) {
        int L = 0;
        int R = products.length - 1;
        while (L < R) {
            int M = L + R >> 1;
            if (products[M].compareTo(s) >= 0) R = M;
            else L = M + 1;
        }
        return L;
    }
}