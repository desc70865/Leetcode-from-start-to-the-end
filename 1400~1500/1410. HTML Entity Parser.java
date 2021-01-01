/* 
HTML entity parser is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.

The special characters and their entities for HTML are:

Quotation Mark: the entity is &quot; and symbol character is ".
Single Quote Mark: the entity is &apos; and symbol character is '.
Ampersand: the entity is &amp; and symbol character is &.
Greater Than Sign: the entity is &gt; and symbol character is >.
Less Than Sign: the entity is &lt; and symbol character is <.
Slash: the entity is &frasl; and symbol character is /.
Given the input text string to the HTML parser, you have to implement the entity parser.

Return the text after replacing the entities by the special characters.

 

Example 1:

Input: text = "&amp; is an HTML entity but &ambassador; is not."
Output: "& is an HTML entity but &ambassador; is not."
Explanation: The parser will replace the &amp; entity by &
Example 2:

Input: text = "and I quote: &quot;...&quot;"
Output: "and I quote: \"...\""
Example 3:

Input: text = "Stay home! Practice on Leetcode :)"
Output: "Stay home! Practice on Leetcode :)"
Example 4:

Input: text = "x &gt; y &amp;&amp; x &lt; y is always false"
Output: "x > y && x < y is always false"
Example 5:

Input: text = "leetcode.com&frasl;problemset&frasl;all"
Output: "leetcode.com/problemset/all"
 

Constraints:

1 <= text.length <= 10^5
The string may contain any possible characters out of all the 256 ASCII characters.
 */

class Solution {
    public String entityParser(String text) {
        char[] chars = text.toCharArray();
        char[] result = new char[chars.length];
        int idx = 0;
        int i = 0;
        while(i < chars.length) {
            boolean add = false;
            if (chars[i] == '&') {
                if (i + 6 < chars.length) {
                    if (chars[i + 1] == 'f' && chars[i + 2] == 'r' && chars[i + 3] == 'a' && chars[i + 4] == 's' && chars[i + 5] == 'l' && chars[i + 6] == ';') {
                        result[idx++] = '/';
                        add = true;
                        i += 7;
                        continue;
                    }
                }
                if (i + 5 < chars.length) {
                    if (chars[i + 1] == 'q' && chars[i + 2] == 'u' && chars[i + 3] == 'o' && chars[i + 4] == 't' && chars[i + 5] == ';') {
                        result[idx++] = '\"';
                        add = true;
                        i += 6;
                        continue;
                    }
                    else if (chars[i + 1] == 'a' && chars[i + 2] == 'p' && chars[i + 3] == 'o' && chars[i + 4] == 's' && chars[i + 5] == ';') {
                        result[idx++] = '\'';
                        add = true;
                        i += 6;
                        continue;
                    }
                }
                if (i + 4 < chars.length) {
                    if (chars[i + 1] == 'a' && chars[i + 2] == 'm' && chars[i + 3] == 'p' && chars[i + 4] == ';') {
                        result[idx++] = '&';
                        add = true;
                        i += 5;
                        continue;
                    }
                }
                if (i + 3 < chars.length) {
                    if (chars[i + 1] == 'g' && chars[i + 2] == 't' && chars[i + 3] == ';') {
                        result[idx++] = '>';
                        add = true;
                        i += 4;
                        continue;
                    }
                    else if (chars[i + 1] == 'l' && chars[i + 2] == 't' && chars[i + 3] == ';') {
                        result[idx++] = '<';
                        add = true;
                        i += 4;
                        continue;
                    }
                }
            }
            if (!add) {
                result[idx++] = chars[i++];
            }
        }
        return new String(result, 0, idx);
    }
}