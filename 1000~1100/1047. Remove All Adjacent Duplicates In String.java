/* 
Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 

Note:

1 <= S.length <= 20000
S consists only of English lowercase letters.
 */

class Solution {
    public String removeDuplicates(String S) {
        Deque<Character> q = new LinkedList<>();
        for (char c: S.toCharArray()) {
            if (! q.isEmpty() && q.peekLast() == c) q.removeLast();
            else q.offerLast(c);
        }
        StringBuilder sb = new StringBuilder();
        while (! q.isEmpty()) sb.append(q.pollFirst());
        return sb.toString();
    }
}



class Solution {
    public String removeDuplicates(String S) {
        char[] res = new char[S.length()];
        int idx = 0;
        for (char c: S.toCharArray()) {
            if (idx == 0 || res[idx - 1] != c) res[idx++] = c;
            else idx--;
        }
        return String.valueOf(res, 0, idx);
    }
}