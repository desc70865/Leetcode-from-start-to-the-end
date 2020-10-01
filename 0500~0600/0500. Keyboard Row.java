/* 
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.

 



 
Example:

Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
 

Note:

You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 */

class Solution {
    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        for(String word : words){
            int first = getLine(word.charAt(0));
            boolean flag = true;
            for(int i = 1; i < word.length(); i++){
                if(getLine(word.charAt(i)) != first){
                    flag = false;
                    break;
                }
            }
            if(flag)
                list.add(word);
        }
        return list.toArray(new String[list.size()]);

    }

    public int getLine(char c){
        char ch = Character.toLowerCase(c);
        if("qwertyuiop".indexOf(ch) != -1)
            return 1;
        else if("asdfghjkl".indexOf(ch) != -1)
            return 2;
        else
            return 3;
    }
}