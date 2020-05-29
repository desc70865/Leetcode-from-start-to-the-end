/* 
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
 */

class Solution {
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (S == null || S.length() == 0 || L == null || L.length == 0)
            return res;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (String str : L) { // 建立哈希表
            if(map.containsKey(str)) {
                map.put(str,map.get(str) + 1);
            }
            else {
                map.put(str, 1);
            }
        }
        int L0 = L[0].length(); // 步长
        for (int i = 0; i < L0; i++) { // L0次循环遍历
            HashMap<String,Integer> curMap = new HashMap<String,Integer>(); // 临时字典
            int count = 0, left = i, j = i; // 在必要时更新 left 以保证滑窗的效率
            while (j <= S.length() - L0) { // 间隔为L0的遍历
                String str = S.substring(j, j + L0);
                // 匹配子串
                if (map.containsKey(str)) { // 找到子串
                    if (curMap.containsKey(str))
                        curMap.put(str,curMap.get(str) + 1);
                    else
                        curMap.put(str, 1);
                    if (curMap.get(str) <= map.get(str))
                        count++;
                    else { // 出现重复项
                        while (curMap.get(str) > map.get(str)) {
                            String temp = S.substring(left,left + L0);
                            curMap.put(temp,curMap.get(temp) - 1); // 去重
                                if (curMap.get(temp) < map.get(temp))
                                    count--; // 误伤计数
                            left += L0;
                        }
                    }
                    if (count == L.length) {
                        res.add(left);
                        // 移除首相并继续匹配
                        String temp = S.substring(left,left + L0);
                        curMap.put(temp,curMap.get(temp) - 1);
                        count--;
                        left += L0;
                    }
                }
                else { // 下一位
                    curMap.clear();
                    count = 0;
                    left = j + L0;
                }
                j += L0;
            }
        }
        return res;
    }
}

// 映射非重复项,步长遍历循环,在首尾操作以降低复杂度?队列