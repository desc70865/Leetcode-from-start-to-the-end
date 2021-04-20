/* 
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.

Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 */

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int len = words.length;
        for (int l = 0, r = 0, size = 0; r < len; r++) {
            int curSize = words[r].length();
            if (size + r - l + curSize > maxWidth) {
                ans.add(justifiedLine(words, l, r - 1, size, maxWidth));
                l = r;
                size = curSize;
            } else {
                size += curSize;
            }
            if (r == len - 1) {
                ans.add(alignLeft(words, l, r, maxWidth));
            }
        }
        return ans;
    }

    private String justifiedLine(String[] words, int l, int r, int size, int maxWidth) {
        if (l == r) {
            return alignLeft(words, l, r, maxWidth);
        }
        int num = r - l;
        int spaceLen = maxWidth - size;
        int fullSpace = spaceLen % num;
        int base = spaceLen / num;
        if (fullSpace == 0) {
            fullSpace = num;
            base--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            sb.append(words[l + i]);
            if (i < num) {
                sb.append(multiSpace(base + (i < fullSpace ? 1 : 0)));
            }
        }
        return sb.toString();
    }

    private String alignLeft(String[] words, int l, int r, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = l; i <= r; i++) {
            sb.append(words[i]);
            if (i < r) {
                sb.append(" ");
            }
        }
        sb.append(multiSpace(maxWidth - sb.length()));
        return sb.toString();
    }

    private String multiSpace(int n) {
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < n; i++) {
            space.append(" ");
        }
        return space.toString();
    }
}