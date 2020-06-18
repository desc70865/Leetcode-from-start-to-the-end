/* 
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

 

Example 1:

Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:

Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:

Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
Example 4:

Input: "/a/./b/../../c/"
Output: "/c"
Example 5:

Input: "/a/../../b/../c//.//"
Output: "/c"
Example 6:

Input: "/a//b////c/d//././/.."
Output: "/a/b/c"
 */

class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        // 首先将字符串以 “/” 分隔存储到新的字符数组 str 中
        String[] str = path.split("/");
        for (String s : str) {
            // 如果数组非空,且访问到的是 “..” 则说明要返回上一级,要将当前元素出栈
            if ( s.equals("..") ) {
                // 这里用到增强型 for 循环不能同时判断，需要再次判空
                // 而普通 for 循环则可写成( !stack.isEmpty() && s.equals("..") )
                if ( !stack.isEmpty() ) {
                    stack.pop();
                }                
            // 如果数组非空并且当前元素不是 “.” 说明当前元素是路径信息，要入栈
            } else if ( !s.equals("") && !s.equals(".") ) {
                stack.push(s);
            }
        }
        // 如果栈内没有元素说明没有路径信息，返回 “/” 即可
        if ( stack.isEmpty() ) {
            return "/";
        }
        // 这里用到 StringBuilder 操作字符串，效率高
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            ans.append( "/" + stack.get(i) );
        }
        return ans.toString();
    }
}

class Solution {
    public String simplifyPath(String path) {
        String paths[] = path.split("/");
        List<String> temp = new ArrayList<>();
        for (String s : paths) {
            if (!s.isEmpty() && !".".equals(s) && !"..".equals(s)) temp.add(s);
            else if (!temp.isEmpty() && "..".equals(s)) temp.remove(temp.size() - 1);
        }
        if (temp.isEmpty()) return "/";
        return "/" + String.join("/", temp);
    }
}

import java.nio.file.*;
class Solution {
    public static String simplifyPath(String path) {
        try {
            return Paths.get(path).normalize().toString().replace("\\", "/");
        } catch (InvalidPathException e) {
            return "/";
        }
    }
}