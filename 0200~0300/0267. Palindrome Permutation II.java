/* 
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
 */

public class Solution {
    Set<String> res = new HashSet<>();
    
    public List<String>generatePalindromes(String s) {
        int[] map = new int[128];
        char[] chs = s.toCharArray();
        int len = chs.length;
        for (char c: chs) map[c]++;
        if (! isValidPalindrome(map)) return Arrays.asList();
        String mid = "";
        for (int i = 0; i < 128; i++) {
            if (map[i] % 2 == 1) {
                mid = String.valueOf((char) i);
                break;
            }
        }
        backTracing(map, mid, new StringBuilder(), len / 2);
        return new ArrayList<>(res);
    }

    private void backTracing(int[] map, String mid, StringBuilder sb, int len) {
        if (sb.length() == len) {
            String s = new StringBuilder(sb.reverse()).append(mid).append(sb.reverse()).toString();
            res.add(s);
            return;
        }
        for (int i = 0; i < 128; i++) {
            if (map[i] < 2) continue;
            map[i] -= 2;
            sb.append((char) i);
            backTracing(map, mid, sb, len);
            sb.setLength(sb.length() - 1);
            map[i] += 2;
        }
    }

    private boolean isValidPalindrome(int[] map) {
        int cnt = 0;
        for (int i = 0; i < 128 && cnt < 2; i++) {
            cnt += map[i] % 2;
        }
        return cnt <= 1;
    }
}



public class Solution {
    Set<String> res = new HashSet<>();
    
    public List<String>generatePalindromes(String s) {
        int[] map = new int[128];
        char[] chs = s.toCharArray();
        int len = chs.length;
        for (char c: chs) map[c]++;
        if (! isValidPalindrome(map)) return Arrays.asList();
        char[] tmp = new char[len];
        if (len % 2 == 1) {
            for (int i = 0; i < 128; i++) {
                if (map[i] % 2 == 1) {
                    tmp[len / 2] = (char) i;
                    break;
                }
            }
        }
        backTracing(map, tmp, 0, len - 1);
        return new ArrayList<>(res);
    }

    private void backTracing(int[] map, char[] tmp, int left, int right) {
        if (left >= right) {
            res.add(new String(tmp));
            return;
        }
        for (int i = 0; i < 128; i++) {
            if (map[i] < 2) continue;
            map[i] -= 2;
            tmp[left] = tmp[right] = (char) i;
            backTracing(map, tmp, left + 1, right - 1);
            map[i] += 2;
        }
    }

    private boolean isValidPalindrome(int[] map) {
        int cnt = 0;
        for (int i = 0; i < 128 && cnt < 2; i++) {
            cnt += map[i] % 2;
        }
        return cnt <= 1;
    }
}