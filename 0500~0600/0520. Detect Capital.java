/* 
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
 

Example 1:

Input: "USA"
Output: True
 

Example 2:

Input: "FlaG"
Output: False
 

Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 */

class Solution {
    public boolean detectCapitalUse(String word) {
        char[] str = word.toCharArray();
        int len = str.length, cnt = 0;
        for (char c: str) cnt += isLowerCase(c);
        if (isLowerCase(str[0]) == 1) return cnt == len;
        else return cnt == 0 || cnt == len - 1;
    }
    
    private int isLowerCase(char c) {
        return c >= 'a' ? 1 : 0;
    }
}



class Solution {
    public boolean detectCapitalUse(String word) {
        char[] str = word.toCharArray();
        int len = str.length, i = 1;
        if (len == 1) return true;
        if (! isLowerCase(str[0]) && ! isLowerCase(str[1])) while (++i < len && ! isLowerCase(str[i])) ;
        else while (i < len && isLowerCase(str[i])) i++;
        return i == len;
    }
    
    private boolean isLowerCase(char c) {
        return c >= 'a';
    }
}