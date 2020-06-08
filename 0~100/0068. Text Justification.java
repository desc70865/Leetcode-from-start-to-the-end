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
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        int i = 0, index = 0, length = 0;
        int width = maxWidth+1;
        while (index < words.length) {
        	width -= words[index].length();
            while (index < words.length && (width -= words[index].length()+1) > 0) {
            	index++;
                i++;
            }
            String temp = "";
            if (index == words.length) {
                for (; i > 1; i--) {
                    temp = words[i] + "" + temp;
                }
                temp = words[--i] + temp;
            } else {
                int space = width / (i-1);
                String l = "";
                for (int k = space; k > 0; k--) {
                	l += " ";
                }
                int first = width - space * i;
                for (; i > 1; i--) {
                    temp = words[i] + l + temp;
                }
                temp = words[--i] + l + temp;
            }
            //System.out.println(temp);
            System.out.println(index);
            res.add(temp);
            width = maxWidth;
        }
        return res;
    }
}

// 咕咕咕,下次一定

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        
        List<String> result = new ArrayList<String>();
        int num = 0, j = 0 ,space = 0,pos = 0,more = 0;
        int[] ans = new int[maxWidth+1];
        char[] Ch = new char[maxWidth]; // 存储每行内容的字符数组
        int i = 0;
        while (i < words.length) {
            j = 0; // 计数
            num = 0;
            while (i < words.length && num+words[i].length()<=maxWidth) {
                num += words[i].length(); // 除空格长度
                ans[j] = i;
                i++;
                j++;
                num++; // 补空格
            }
            pos = 0; // Ch[pos]
            if (i == words.length) { // 最后一行
                space = 1;
                more = maxWidth-num; // ??? 公共变量, 末尾补空格
            } else if (j == 1) { // 仅一个单词
                space = maxWidth - (num-1);
                more = 0;
            } else {
                space = (maxWidth-num+j) / (j-1); // j-1 段空格的长度
                more = (maxWidth-num+j) % (j-1); // 其中 m 个需要 +1
            }
            for (int k = 0; k < j; k++) {
                for (int a = 0; a < words[ans[k]].length(); a++,pos++)
                    Ch[pos] = words[ans[k]].charAt(a);
                for (int a = 0; a < space && pos < maxWidth; a++,pos++)
                    Ch[pos] = ' ';
                if (i != words.length && more > 0 && pos < maxWidth) {
                    Ch[pos] = ' ';
                    more--;
                    pos++;
                }
                if (i == words.length && k == j-1) {
                    for (int a = 0; a < more; a++,pos++)
                        Ch[pos] = ' ';
                }
                
            }
            result.add(String.valueOf(Ch));
        }
        return result;
    }
}