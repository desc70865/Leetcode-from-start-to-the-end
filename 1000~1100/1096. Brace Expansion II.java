/* 
Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.

Grammar can best be understood through simple examples:

Single letters represent a singleton set containing that word.
R("a") = {"a"}
R("w") = {"w"}
When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
R("{a,b,c}") = {"a","b","c"}
R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
Formally, the 3 rules for our grammar:

For every lowercase letter x, we have R(x) = {x}
For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.

 

Example 1:

Input: "{a,b}{c,{d,e}}"
Output: ["ac","ad","ae","bc","bd","be"]
Example 2:

Input: "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.
 

Constraints:

1 <= expression.length <= 60
expression[i] consists of '{', '}', ','or lowercase English letters.
The given expression represents a set of words based on the grammar given in the description.
 */

class Solution {
    public List<String> braceExpansionII(String expression) {
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(expression);
        while (! queue.isEmpty()) {
            if (queue.peek().indexOf('}') == -1) {
                break;
            }
            int size = queue.size();
            while (--size >= 0) {
                String exp = queue.poll();
                int right = exp.indexOf('}');
                int left = exp.lastIndexOf('{', right);
                String head = exp.substring(0, left);
                String tail = exp.substring(right + 1);
                for (String mid: exp.substring(left + 1, right).split(",")) {
                    queue.offer(new StringBuilder(head).append(mid).append(tail).toString());
                }
            }
        }
        List<String> ans = new ArrayList<>(new HashSet<>(queue));
        Collections.sort(ans);
        return ans;
    }
}



class Solution {
    String expression;
    char[] s;
    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.s = expression.toCharArray();
        return new ArrayList<>(eval(0, s.length - 1));
    }

    private TreeSet<String> eval(int left, int right) {
        Expression[] exp = new Expression[right - left + 1];
        int[] x = new int[2]; // 0, index; 1, CP status;
        for (int l = left; l <= right; ++l) {
            if (s[l] == '{') {
                int r = findNextBracket(l + 1);
                update(x, exp, new Expression(eval(l + 1, r - 1)));
                l = r;
            } else if (s[l] == ',') {
                x[1] = 1;
                continue;
            } else {
                int r = findEndOfString(l);
                update(x, exp, new Expression(expression.substring(l, r)));
                l = r - 1;
            }
            x[1] = 0;
        }
        TreeSet<String> ans = new TreeSet<>();
        for (int p = x[0] - 1; p >= 0; --p) {
            ans.addAll(exp[p].list);
        }
        // ans.print();
        return ans;
    }

    private void update(int[] x, Expression[] exp, Expression obj) {
        if (x[1] == 0 && x[0] > 0) {
            exp[x[0] - 1].CartesianProduct(obj);
        } else {
            exp[x[0]++] = obj;
        }
    }

    private int findEndOfString(int idx) {
        int i = idx + 1;
        for (; i < s.length; ++i) {
            if (s[i] < 'a' || 'z' < s[i]) {
                break;
            }
        }
        return i;
    }

    private int findNextBracket(int idx) {
        for (int bracket = 1; bracket > 0; ++idx) {
            if (s[idx] == '{') {
                ++bracket;
            } else if (s[idx] == '}') {
                --bracket;
            }
        }
        return idx - 1;
    }
}

class Expression {
    TreeSet<String> list;

    public Expression() {
        this.list = new TreeSet<>();
    }

    public Expression(String s) {
        this.list = new TreeSet<>();
        this.list.add(s);
    }

    public Expression(TreeSet<String> exp) {
        this.list = new TreeSet<>();
        this.list.addAll(exp);
    }

    public void add(TreeSet<String> exp) {
        this.list.addAll(exp);
    }

    public void CartesianProduct(Expression exp) {
        TreeSet<String> ans = new TreeSet<>();
        for (String a: this.list) {
            for (String b: exp.list) {
                ans.add(a + b);
            }
        }
        this.list = ans;
    }

    public void print() {
        System.out.println(list);
    }
}