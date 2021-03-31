/* 
Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.

 

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 

Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii/solution/ii-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public String removeDuplicates(String s, int k) {
        char[] res = new char[s.length()];
        int idx = 0;
        for (char c: s.toCharArray()) {
            if (idx < k - 1 || magic(res, idx, k, c)) res[idx++] = c;
            else idx -= k - 1;
        }
        return String.valueOf(res, 0, idx);
    }

    private boolean magic(char[] A, int end, int k, char c) {
        for (int i = end - 1; i >= end - k + 1; i--) {
            if (A[i] != c) return true;
        }
        return false;
    }
}



class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<Integer> p = new LinkedList<>();
        char[] str = new char[s.length()];
        int idx = 0;
        for (char c: s.toCharArray()) {
            str[idx] = c;
            if (idx == 0 || str[idx] != str[idx - 1]) p.push(1);
            else {
                int tmp = 1 + p.pop();
                if (tmp == k) idx -= k;
                else p.push(tmp);
            }
            idx++;
        }
        return new String(str, 0, idx);
    }
}



class Solution {
    public String removeDuplicates(String s, int k) {
        int N = s.length();
        
        int[] cnt = new int[N];
        int p = -1;
        
        char[] str = new char[N];
        int idx = -1;
        
        for (char c: s.toCharArray()) {
            str[++idx] = c;
            if (idx == 0 || str[idx] != str[idx - 1]) {
                cnt[++p]++;
            } else if (++cnt[p] == k) {
                cnt[p--] = 0;
                idx -= k;
            }
        }
        
        return new String(str, 0, idx + 1);
    }
}