/* 
A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
 

Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
 

Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
 

Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3
 */

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        int N = bank.length;
        int[] v = new int[N];
        int len = start.length();
        char[][] str = new char[N][len];
        char[] ends = end.toCharArray();
        for (int i = 0; i < N; i++) str[i] = bank[i].toCharArray();
        Queue<char[]> q = new LinkedList<>();
        q.offer(start.toCharArray());
        int res = 0;
        while (! q.isEmpty()) {
            int k = q.size();
            for (int i = 0; i < k; i++) {
                char[] s = q.poll();
                if (Arrays.equals(s, ends)) return res;
                for (int j = 0; j < N; j++) {
                    if (v[j] > 0) continue;
                    if (diff(s, str[j])) {
                        q.offer(str[j]);
                        v[j] = res + 1;
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private boolean diff(char[] a, char[] b) {
        int N = a.length;
        int c = 0;
        for (int i = 0; i < N && c < 2; i++) {
            if (a[i] != b[i]) c++;
        }
        return c == 1;
    }
}