/* 
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy. 

 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 

Constraints:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters
 */

class Solution {
    public int expressiveWords(String S, String[] words) {
        int max = S.length();
        if (max == 0) return 0;
        int[][] m = helper(S);
        int min = 0;
        for (int num: m[1]) {
            if (num < 3) min += num;
            else min++;
        }
        int res = 0;
        for (String word: words) {
            int len = word.length();
            if (len < min || len > max) continue;
            int[][] tmp = helper(word);
            if (! Arrays.equals(m[0], tmp[0])) continue;
            if (check(m[1], tmp[1])) res++;
        }
        return res;
    }

    private boolean check(int[] A, int[] B) {
        int len = A.length;
        for (int i = 0; i < len; i++) {
            if (A[i] < 3) {
                if (A[i] != B[i]) return false;
            } else {
                if (A[i] < B[i]) return false;
            }
        }
        return true;
    }

    private int[][] helper(String s) {
        char[] str = s.toCharArray();
        int len = str.length;
        int count = 1;
        char pre = str[0];
        int[] map = new int[len];
        int[] cnt = new int[len];
        int idx = 0;
        for (int i = 1; i < len; i++) {
            if (str[i] == pre) count++;
            else {
                map[idx] = pre - 'a';
                cnt[idx++] = count;
                count = 1;
                pre = str[i];
            }
        }
        map[idx] = pre - 'a';
        cnt[idx++] = count;
        return new int[][] {Arrays.copyOfRange(map, 0, idx), Arrays.copyOfRange(cnt, 0, idx)};
    }
}