/* 
Special binary strings are binary strings with the following two properties:

The number of 0's is equal to the number of 1's.
Every prefix of the binary string has at least as many 1's as 0's.
Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)

At the end of any number of moves, what is the lexicographically largest resulting string possible?

Example 1:
Input: S = "11011000"
Output: "11100100"
Explanation:
The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
This is the lexicographically largest string possible after some number of swaps.
Note:

S has length at most 50.
S is guaranteed to be a special binary string as defined above.
 */

class Solution {
    public String makeLargestSpecial(String S) {
        List<String> list = new ArrayList<>();
        char[] chs = S.toCharArray();
        for (int cnt = 0, left = 0, right = 0; right < chs.length; right++) {
            if (chs[right] == '1') cnt++;
            else if (--cnt == 0) {
                list.add(new StringBuilder("1").append(makeLargestSpecial(S.substring(left + 1, right))).append("0").toString());
                left = right + 1;
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return String.join("", list);
    }
}



class Solution {
    public String makeLargestSpecial(String S) {
        String[] list = new String[S.length() / 2];
        int size = 0;
        char[] chs = S.toCharArray();
        for (int cnt = 0, left = 0, right = 0; right < chs.length; right++) {
            if (chs[right] == '1') cnt++;
            else if (--cnt == 0) {
                list[size++] = new StringBuilder("1").append(makeLargestSpecial(S.substring(left + 1, right))).append("0").toString();
                left = right + 1;
            }
        }
        sort(list, 0, size - 1);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < size; i++) {
            ans.append(list[i]);
        }
        return ans.toString();
    }

    private void sort(String[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }
    
    public int partition(String[] arr, int l, int r) {
        String pivot = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r].compareTo(pivot) > 0) {
                    arr[l++] = arr[r];
                    break;
                }
                r--;
            }
            while (l < r) {
                if (arr[l].compareTo(pivot) < 0) {
                    arr[r--] = arr[l];
                    break;
                }
                l++;
            }
        }
        arr[l] = pivot;
        return l;
    }
}