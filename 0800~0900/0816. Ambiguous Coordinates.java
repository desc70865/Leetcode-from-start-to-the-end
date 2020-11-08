/* 
We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string S.  Return a list of strings representing all possibilities for what our original coordinates could have been.

Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)

Example 1:
Input: "(123)"
Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
Example 2:
Input: "(00011)"
Output:  ["(0.001, 1)", "(0, 0.011)"]
Explanation: 
0.0, 00, 0001 or 00.01 are not allowed.
Example 3:
Input: "(0123)"
Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
Example 4:
Input: "(100)"
Output: [(10, 0)]
Explanation: 
1.0 is not allowed.
 

Note:

4 <= S.length <= 12.
S[0] = "(", S[S.length - 1] = ")", and the other elements in S are digits.
 */

class Solution {
    public List<String> ambiguousCoordinates(String S) {
        char[] sArr = S.toCharArray();
        List<String> res = new ArrayList<>();

        for (int i = 1; i < sArr.length-2; i++) {
            if (check(sArr, 1, i+1, -1) && check(sArr, i+1, sArr.length-1, -1)) {
                res.add(new StringBuilder().append(sArr, 0, i+1).append(", ").append(sArr, i+1, sArr.length-i-1).toString());
            }

            for (int j = 1; j < i; j++) {
                if (check(sArr, 1, i+1, j) ) {
                    if (check(sArr, i+1, sArr.length-1, -1)) {
                        res.add(new StringBuilder().append(sArr, 0, j+1).append('.').append(sArr, j+1, i-j).append(", ").append(sArr, i+1, sArr.length-i-1).toString());
                    }

                    for (int k = i+1; k < sArr.length-2; k++) {
                        if (check(sArr, i+1, sArr.length-1, k)) {
                            res.add(new StringBuilder().append(sArr, 0, j+1).append('.').append(sArr, j+1, i-j).append(", ").append(sArr, i+1, k-i).append('.').append(sArr, k+1, sArr.length-k-1).toString());
                        }
                    }
                }
            }

            if (check(sArr, 1, i+1, -1)) {
                for (int j = i+1; j < sArr.length-2; j++) {
                    if (check(sArr, i+1, sArr.length-1, j)) {
                        res.add(new StringBuilder().append(sArr, 0, i+1).append(", ").append(sArr, i+1, j-i).append('.').append(sArr, j+1, sArr.length-j-1).toString());
                    }
                }
            }
        }
        return res;
    }

    private boolean check(char[] sArr, int l, int r, int q) {
        if (q == -1) {
            if (r-l > 1 && sArr[l] == '0') {
                return false;
            }
        } else {
            if (r-q-1 > 0 && sArr[r-1] == '0') {
                return false;
            }

            if (q-l > 0 && sArr[l] == '0') {
                return false;
            }
        }
        return true;
    }
}