/* 
Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"
 */

class Solution {
    String[] nums = new String[] {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    int[] map;
    public String originalDigits(String s) {
        map = new int[26];
        for (char c: s.toCharArray()) map[c - 97]++;
        int[] cnt = new int[10];
        remove(0, cnt, 'z');
        remove(2, cnt, 'w');
        remove(4, cnt, 'u');
        remove(6, cnt, 'x');
        remove(8, cnt, 'g');
        remove(3, cnt, 't');
        remove(5, cnt, 'f');
        remove(7, cnt, 'v');
        remove(9, cnt, 'i');
        remove(1, cnt, 'e');
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    private void remove(int idx, int[] cnt, char key) {
        cnt[idx] = map[key - 97];
        if (cnt[idx] == 0) return;
        for (char c: nums[idx].toCharArray()) {
            map[c - 97] -= cnt[idx];
        }
    }
}