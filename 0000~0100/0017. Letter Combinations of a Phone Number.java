/* 
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.  
 */

class Solution {
    public List<String> letterCombinations(String digits) {
        HashMap<Character, char[]> dict = new HashMap<Character, char[]>();
        dict.put('2',new char[]{'a','b','c'});
        dict.put('3',new char[]{'d','e','f'});
        dict.put('4',new char[]{'g','h','i'});
        dict.put('5',new char[]{'j','k','l'});
        dict.put('6',new char[]{'m','n','o'});
        dict.put('7',new char[]{'p','q','r','s'});
        dict.put('8',new char[]{'t','u','v'});
        dict.put('9',new char[]{'w','x','y','z'});
     
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
     
        char[] arr = new char[digits.length()];
        helper(digits, 0, dict, res, arr);
     
        return res;
    }
     
    private void helper(String digits, int index, HashMap<Character, char[]> dict, List<String> res, char[] arr){
        if (index == digits.length()) {
            res.add(new String(arr));
            return;
        }
     
        char number = digits.charAt(index);
        char[] candidates = dict.get(number);
        for (int i = 0; i < candidates.length; i++) {
            arr[index] = candidates[i];
            helper(digits, index+1, dict, res, arr);
        }
    }
}

// 递归/迭代
// 深度优先,广度优先

class Solution {
    Map<Character, char[]> dict = new HashMap<>();
    List<String> res = new ArrayList<String>();
    
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;

        dict.put('2', new char[]{'a','b','c'});
        dict.put('3', new char[]{'d','e','f'});
        dict.put('4', new char[]{'g','h','i'});
        dict.put('5', new char[]{'j','k','l'});
        dict.put('6', new char[]{'m','n','o'});
        dict.put('7', new char[]{'p','q','r','s'});
        dict.put('8', new char[]{'t','u','v'});
        dict.put('9', new char[]{'w','x','y','z'});
        
        helper(digits.toCharArray(), 0, new char[digits.length()]);
        
        return res;
    }
    
    private void helper(char[] digits, int index, char[] arr){
        if (index == digits.length) {
            res.add(new String(arr));
            return ;
        }
        
        char num = digits[index];
        char[] candidates = dict.get(num);
        for (int i = 0; i < candidates.length; i++) {
            arr[index] = candidates[i];
            helper(digits, index+1, arr);
        }
    }
}