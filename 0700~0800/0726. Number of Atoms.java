/* 
Given a chemical formula (given as a string), return the count of each atom.

The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together to produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

 

 

Example 1:

Input: formula = "H2O"
Output: "H2O"
Explanation: The count of elements are {'H': 2, 'O': 1}.
Example 2:

Input: formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:

Input: formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Example 4:

Input: formula = "Be32"
Output: "Be32"
 

Constraints:

1 <= formula.length <= 1000
formula consists of English letters, digits, '(', and ')'.
formula is always valid.
 */

class Solution {
    char[] s;

    public String countOfAtoms(String formula) {
        this.s = formula.toCharArray();
        TreeMap<String, Integer> map = eval(0, s.length - 1);
        StringBuilder sb = new StringBuilder();
        for (String key: map.keySet()) {
            // System.out.println(key + "---" + map.get(key));
            if (map.get(key) == 1) {
                sb.append(key);
            } else {
                sb.append(key).append(map.get(key));
            }
        }
        return sb.toString();
    }

    private TreeMap<String, Integer> eval(int l, int r) {
        // System.out.println(l + "---" + r);
        TreeMap<String, Integer> ansMap = new TreeMap<>((a, b) -> a.compareTo(b));
        for (int i = l; i <= r; ++i) {
            if (s[i] == '(') {
                int j = findNext(i + 1);
                int count = 0;
                int k = j + 1;
                for (; k <= r && isNum(s[k]); ++k) {
                    count *= 10;
                    count += s[k] - '0';
                }
                if (count == 0) {
                    count = 1;
                }
                append(ansMap, eval(i + 1, j - 1), count);
                i = k - 1;
            } else {
                StringBuilder atom = new StringBuilder();
                atom.append(s[i]);
                int k = i + 1;
                for (; k <= r && isLowCase(s[k]); ++k) {
                    atom.append(s[k]);
                }
                int count = 0;
                for (; k <= r && isNum(s[k]); ++k) {
                    count *= 10;
                    count += s[k] - '0';
                }
                i = k - 1;
                if (count == 0) {
                    count = 1;
                }
                ansMap.merge(atom.toString(), count, Integer::sum);
            }
        }
        return ansMap;
    }

    private void append(TreeMap<String, Integer> map, TreeMap<String, Integer> add, int x) {
        for (String key: add.keySet()) {
            // System.out.println(key + "---" + add.get(key) + "---" + x);
            map.merge(key, add.get(key) * x, Integer::sum);
        }
        // System.out.println("----");
    }

    private int findNext(int x) {
        int ans = x;
        for (int bracket = 1; bracket != 0; ++ans) {
            if (s[ans] == '(') {
                ++bracket;
            } else if (s[ans] == ')') {
                --bracket;
            }
        }
        return ans - 1;
    }

    private boolean isNum(char c) {
        return '0' <= c && c <= '9';
    }

    private boolean isLowCase(char c) {
        return 'a' <= c && c <= 'z';
    }
}