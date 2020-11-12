/* 
Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.
And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:
Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
where '-' represents the looped status. 
The answer string came from the fourth looped one, 
where you could cut from the middle character 'a' and get "zyxcba".
Note:
The input strings will only contain lowercase letters.
The total length of all the strings will not over 1,000.
 */

class Solution {
    public String splitLoopedString(String[] strs) {
        String[] backward = new String[strs.length];
        StringBuilder curr = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            String reverse = new StringBuilder(strs[i]).reverse().toString();
            if (strs[i].compareTo(reverse) >= 0) {
                backward[i] = reverse;
            } else {
                backward[i] = strs[i];
                strs[i] = reverse;
            }
            curr.append(strs[i]);
        }
        int idx = 0;
        String ans = curr.toString();
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                String temp = curr.toString();
                if (temp.compareTo(ans) > 0) {
                    ans = temp;
                }
                curr.append(curr.charAt(0));
                curr.deleteCharAt(0);
            }
            
            curr.delete(curr.length()-strs[i].length(), curr.length());
            curr.insert(0, backward[i]);
            for (int j = 0; j < strs[i].length(); j++) {
                String temp = curr.toString();
                if (temp.compareTo(ans) > 0) {
                    ans = temp;
                }
                curr.append(curr.charAt(0));
                curr.deleteCharAt(0);
            }
            curr.delete(curr.length()-strs[i].length(), curr.length());
            curr.append(strs[i]);
        }
        return ans;
    }
}